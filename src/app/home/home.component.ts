import { Component, OnInit } from '@angular/core';
import { AppService } from '../core/app.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Demo';
  greeting: any;

  constructor(private app: AppService, private http: HttpClient) {}

  authenticated() { return this.app.authenticated; }

  ngOnInit(): void {
    this.http.get('resource').subscribe(data => this.greeting = data);
  }

}
