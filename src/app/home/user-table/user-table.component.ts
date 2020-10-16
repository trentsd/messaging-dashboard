import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/shared/interfaces/user.model';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  constructor() { }

  @Input() userList: Array<User>

  ngOnInit(): void {
  }

}
