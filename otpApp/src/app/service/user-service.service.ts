import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Email } from '../model/email';

import { Check } from '../model/check';
import { Validate } from '../model/validate'
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent } from 'rxjs';
@Injectable()
export class UserService{
  private emailUrl: string;
  private emailUrl1: string;

  constructor(private http: HttpClient) {
    this.emailUrl = 'http://localhost:8081/emails';
    this.emailUrl1 = 'http://localhost:8081/validate';
  }

  public get(): Observable<Check> {
    return this.http.get<Check>(`${this.emailUrl}`);
  }

  public validate(validate: Validate){
    return this.http.post<Validate>(this.emailUrl1,validate);
  }

  public save(email: Email) {
    return this.http.post<Email>(this.emailUrl, email);
  }
}
