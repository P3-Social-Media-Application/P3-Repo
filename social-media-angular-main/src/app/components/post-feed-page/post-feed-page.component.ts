import {
	ChangeDetectorRef,
	Component,
	ElementRef,
	OnInit,
	ViewChild,
} from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import Post from "src/app/models/Post";
import User from "src/app/models/User";
import { AuthService } from "src/app/services/auth.service";
import { PostService } from "src/app/services/post.service";

@Component({
	selector: "app-post-feed-page",
	templateUrl: "./post-feed-page.component.html",
	styleUrls: ["./post-feed-page.component.css"],
})
export class PostFeedPageComponent implements OnInit {
	postForm = new FormGroup({
		text: new FormControl(""),
		imageUrl: new FormControl(""),
	});

	posts: Post[] = [];
	createPost: boolean = false;
	currentUser: User = this.authService.currentUser;

	constructor(
		private postService: PostService,
		private authService: AuthService
	) {}

	ngOnInit(): void {
		this.postService.getAllPosts().subscribe((response) => {
			this.posts = response.filter((post)=>
				post.comment == false
			);
		});
	}

	toggleCreatePost = () => {
		this.createPost = !this.createPost;
	};

	submitPost = (e: any) => {
		e.preventDefault();
		this.postService
			.upsertPost(
				new Post(
					0,
					this.postForm.value.text || "",
					this.postForm.value.imageUrl || "",
					this.authService.currentUser,
					[],
					false
				)
			)
			.subscribe((response) => {
				this.posts = [response, ...this.posts];
				this.toggleCreatePost();
			});
	};

	refreshPosts = (deleteBool: boolean) => {
		if (deleteBool) {
			this.postService.getAllPosts().subscribe((response) => {
				this.posts = response.filter((post)=>
				post.comment == false
			);
			});
		}
	};
}
