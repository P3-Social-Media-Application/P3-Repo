import { Component, Input, OnInit, Output, EventEmitter } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import Post from "src/app/models/Post";
import User from "src/app/models/User";
import { AuthService } from "src/app/services/auth.service";
import { PostService } from "src/app/services/post.service";

@Component({
	selector: "app-comment",
	templateUrl: "./comment.component.html",
	styleUrls: ["./comment.component.css"],
})
export class CommentComponent implements OnInit {
	commentForm = new FormGroup({
		text: new FormControl(""),
	});

	@Input("comment") inputComment: Post;
	replyToComment: boolean = false;

	@Input()
	currentUser:User;
  
	@Output()
	deleteCommentEvent = new EventEmitter();

	@Output()
	commentEventEmitter = new EventEmitter();

	constructor(
		private postService: PostService,
		private authService: AuthService
	) {}

	ngOnInit(): void {}

	toggleReplyToComment = () => {
		this.replyToComment = !this.replyToComment;
	};

	submitReply = (e: any) => {
		e.preventDefault();
		let newComment = new Post(
			0,
			this.commentForm.value.text || "",
			"",
			this.currentUser,
			[],
			true
		);
		this.postService
			.upsertPost({
				...this.inputComment,
				comments: [...this.inputComment.comments, newComment],
			})
			.subscribe((response) => {
				this.inputComment = response;
				this.toggleReplyToComment();
				this.commentEventEmitter.emit(true);
			});
	};

	deleteComment = (post: Post) => {
		if (
			window.confirm("Are sure you want to remove your comment?")
		) {
			this.postService.deleteComment(post).subscribe();
			setTimeout(() => {
			this.deleteCommentEvent.emit(true);
		}, 250);
		}
	};
}
