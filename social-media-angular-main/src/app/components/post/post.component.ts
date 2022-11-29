import { outputAst } from "@angular/compiler";
import { Component, Input, OnInit, Output, EventEmitter } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import Post from "src/app/models/Post";
import User from "src/app/models/User";
import { AuthService } from "src/app/services/auth.service";
import { PostService } from "src/app/services/post.service";

@Component({
	selector: "app-post",
	templateUrl: "./post.component.html",
	styleUrls: ["./post.component.css"],
})
export class PostComponent implements OnInit {
	commentForm = new FormGroup({
		text: new FormControl(""),
	});

	@Input("post") post: Post;
	replyToPost: boolean = false;

	@Input()
	currentUser: User;

	@Output()
	deletePostEvent = new EventEmitter();

	constructor(
		private postService: PostService,
		private authService: AuthService
	) { }

	ngOnInit(): void { }

	toggleReplyToPost = () => {
		this.replyToPost = !this.replyToPost;
	};

	submitReply = (e: any) => {
		e.preventDefault();
		let newComment = new Post(
			0,
			this.commentForm.value.text || "",
			"",
			this.authService.currentUser,
			[],
			true
		);
		this.postService
			.upsertPost({
				...this.post,
				comments: [...this.post.comments, newComment],
			})
			.subscribe((response) => {
				this.post = response;
				this.toggleReplyToPost();
			});
	};

	deletePost = (post: Post) => {
		if (
			window.confirm("Are sure you want to remove your comment?")
		) {
			this.postService.deletePost(post).subscribe();
			setTimeout(() => {
				this.deletePostEvent.emit(true);
			}, 250);
		}
	};

	refreshPosts = (refresh: boolean) => {
		this.deletePostEvent.emit(true);
	};
}
