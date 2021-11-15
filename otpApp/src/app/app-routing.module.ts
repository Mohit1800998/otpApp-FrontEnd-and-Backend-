import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmailListComponent } from './email-list/email-list.component';
import { EmailFormComponent } from './email-form/email-form.component';

import { ValidateFormComponent } from './validate-form/validate-form.component';
 

const routes: Routes = [
  { path: 'emails', component: EmailListComponent },
  { path: 'adduser', component: EmailFormComponent },
  { path: 'validate', component: ValidateFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class AppRoutingModule { }
