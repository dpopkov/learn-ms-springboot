import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http";
import { ChallengeComponent } from './challenge/challenge.component';
import {FormsModule} from "@angular/forms";
import { LastAttemptsComponent } from './challenge/last-attempts/last-attempts.component';

@NgModule({
  declarations: [
    AppComponent,
    LastAttemptsComponent,
    ChallengeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
