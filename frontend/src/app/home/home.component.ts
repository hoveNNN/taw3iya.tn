import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PostComponent } from '../post/post.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    constructor(private modalService:NgbModal){}
  ngOnInit(): void {
      
  }
  openPost() {
    this.modalService.open(PostComponent); // Open the modal component directly
  }
}
