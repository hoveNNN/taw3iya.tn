import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  post: any = {
    prefix: '',
    title: '',
    content: '',
    subreddit: '',
    image: null,
    video: null
  };

  constructor(private modalService: NgbModal) {}

  ngOnInit() {}

  

  onSubmit() {
    
    this.modalService.dismissAll(); // Close the modal after submission
  }
  onFileChange(event: Event, fileType: string) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      if (fileType === 'image') {
        this.post.image = file;
      } else if (fileType === 'video') {
        this.post.video = file;
      }
    }
  }
}
