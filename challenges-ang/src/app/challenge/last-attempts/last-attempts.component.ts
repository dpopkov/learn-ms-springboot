import {Component, Input, OnInit} from '@angular/core';
import {ChallengeAttemptResult} from "../../model/challenge-attempt-result";

@Component({
  selector: 'app-last-attempts',
  templateUrl: './last-attempts.component.html',
  styleUrls: ['./last-attempts.component.css']
})
export class LastAttemptsComponent implements OnInit {

  @Input()
  lastAttempts = new Array<ChallengeAttemptResult>();

  constructor() { }

  ngOnInit(): void {
  }

}
