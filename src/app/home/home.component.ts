import { Component, OnInit } from '@angular/core';
import { AppService } from '../core/app.service';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { trimTrailingNulls } from '@angular/compiler/src/render3/view/util';

import { User } from '../shared/interfaces/user.model';

//This interface is just to appease angular while I play with different functionalities.
export interface Greeting {
  id: number;
  content: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'Demo';
  greeting: Greeting = {id: null, content: null};

  userList: any;

  constructor(private app: AppService, private http: HttpClient) {}

  authenticated() { return this.app.authenticated; }

  ngOnInit(): void {
    this.http.get('home/api').subscribe(data => {this.userList = data;});

    if (this.authenticated()) {
    this.http.get('home/api').subscribe((data: Greeting) =>
      this.greeting = data
      );
    }
  }

}
