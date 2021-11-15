import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmailListComponent } from './email-list/email-list.component';
import { EmailFormComponent } from './email-form/email-form.component';
import { UserService } from './service/user-service.service';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ValidateFormComponent } from './validate-form/validate-form.component'; // <== add the imports!

@NgModule({
  declarations: [
    AppComponent,
    EmailListComponent,
    EmailFormComponent,
    ValidateFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,                               // <========== Add this line!
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
