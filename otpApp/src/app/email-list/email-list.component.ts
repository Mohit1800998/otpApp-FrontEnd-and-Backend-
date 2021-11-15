import { Component, OnInit } from '@angular/core';

import { Email } from '../model/email';

import { Check } from '../model/check';
import { UserService } from '../service/user-service.service';
@Component({
  selector: 'app-email-list',
  templateUrl: './email-list.component.html',
  styleUrls: ['./email-list.component.css']
})
export class EmailListComponent implements OnInit {
  
  check :Check= new Check();
  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.get().subscribe(data => {
      this.check = data;

      console.log(this.check);
    });
  }


}
