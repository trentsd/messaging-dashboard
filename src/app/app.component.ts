import { Component } from '@angular/core';
import { AppService } from './core/app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { finalize } from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Demo';
  greeting: any;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {}

  logout() {
    this.http.post('logout', {}).pipe(
      finalize(() => {
        this.app.authenticated = false;
        this.router.navigateByUrl('/login');
      })).subscribe();
  }

  ngOnInit() {
    this.app.authenticate(undefined, undefined);
  }
}
