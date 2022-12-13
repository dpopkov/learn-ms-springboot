import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ChallengeComponent} from "./challenge/challenge.component";

const routes: Routes = [
  {path: '', component: ChallengeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
