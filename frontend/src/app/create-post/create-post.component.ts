import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PostService } from '../services/post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  
  postForm!: FormGroup;
  tags: string[] = [];

  constructor(private fb: FormBuilder, private router: Router, private postService: PostService) {}

  ngOnInit(): void {
    this.postForm = this.fb.group({
      name: [null, Validators.required],
      content: [null, [Validators.required, Validators.maxLength(5000)]],
      img: [null],
      postedBy: [null, Validators.required]
    });   
  }

  add(event: any): void {
    const value = (event.target.value || '').trim();
    if (value) {
      this.tags.push(value);
    }
    event.target.value = '';
  }

  remove(tag: string): void {
    const index = this.tags.indexOf(tag);
    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }

  createPost(): void {
    const data = this.postForm.value;
    data.tags = this.tags;
    this.postService.createNewPost(data).subscribe(
      res => {
        alert("Post created successfully!");
        this.router.navigateByUrl("/");
      },
      error => {
        alert("Something went wrong!");
      }
    );
  }
}
