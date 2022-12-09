import {
	ChangeDetectorRef,
	Component,
	ElementRef,
	Input,
	OnInit,
	ViewChild,
} from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import Post from "src/app/models/Post";
import User from "src/app/models/User";
import { AuthService } from "src/app/services/auth.service";
import { PostService } from "src/app/services/post.service";
import "leo-profanity";

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
	currentUser: User;

	constructor(
		private postService: PostService,
		private authService: AuthService
	) {}

	ngOnInit(): void {
		this.postService.getAllPosts().subscribe((response) => {
			this.posts = response.filter((post) => post.comment == false);
		});

		this.authService.retrieveUser().subscribe((response) => {
			this.currentUser = response;
		});
	}

	toggleCreatePost = () => {
		this.createPost = !this.createPost;
	};

	submitPost = (e: any) => {
		e.preventDefault();
		const Filter = require("leo-profanity");
		this.postService
			.upsertPost(
				new Post(
					0,
					Filter.clean(this.postForm.value.text || ""),
					this.postForm.value.imageUrl || "",
					this.currentUser,
					[],
					false
				)
			)
			.subscribe((response) => {
				this.posts = [response, ...this.posts];
				this.toggleCreatePost();
				window.location.reload();
			});
	};

	refreshPosts = (deleteBool: boolean) => {
		if (deleteBool) {
			this.postService.getAllPosts().subscribe((response) => {
				console.log("getting all posts");
				this.posts = response.filter((post) => post.comment == false);
			});
		}
	};
}
