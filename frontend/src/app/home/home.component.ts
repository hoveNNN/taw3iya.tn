import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PostComponent } from '../post/post.component';
interface Post {
  title: string;
  time: string;
  author: string;
  tags: string[];
  votes: number;
  replies: number;
  views: number;
}
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    constructor(private modalService:NgbModal){}

  posts: Post[] = [
    { title: 'mawdhou3', time: '20 minutes', author: 'msahli', tags: ['hashtag'], votes: 141, replies: 122, views: 290 },
    { title: 'mawdhou3', time: '54 minutes', author: 'issam', tags: ['hashtag', 'hashtag'], votes: 256, replies: 251, views: 223 },
    // Add more posts here
  ];
  
  filteredPosts: Post[] = [];
  selectedCategory: string = '';
  selectedFilter: string = '';

  ngOnInit(): void {
    this.filteredPosts = this.posts;
  }

  filterPosts(): void {
    let filtered = this.posts;

    if (this.selectedCategory) {
      filtered = filtered.filter(post => post.tags.includes(this.selectedCategory));
    }

    if (this.selectedFilter) {
      switch (this.selectedFilter) {
        case 'Votes':
          filtered = filtered.sort((a, b) => b.votes - a.votes);
          break;
        case 'Replies':
          filtered = filtered.sort((a, b) => b.replies - a.replies);
          break;
        case 'Views':
          filtered = filtered.sort((a, b) => b.views - a.views);
          break;
      }
    }

    this.filteredPosts = filtered;
  }
}
