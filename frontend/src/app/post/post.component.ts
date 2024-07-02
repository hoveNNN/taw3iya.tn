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
    
    this.modalService.dismissAll(); 
  }
  closeModal() {
    this.modalService.dismissAll()
  }
  

}
