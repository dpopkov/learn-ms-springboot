import { Component, OnInit } from '@angular/core';
import {DataService} from "../data.service";
import {Challenge} from "../model/challenge";
import {ChallengeAttemptRequest} from "../model/challenge-attempt-request";

@Component({
  selector: 'app-challenge',
  templateUrl: './challenge.component.html',
  styleUrls: ['./challenge.component.css']
})
export class ChallengeComponent implements OnInit {

  challenge = new Challenge(-1, -1);
  formAttempt = new ChallengeAttemptRequest('', -1, -1, -1);
  message = '';

  constructor(private dataService: DataService) { }

  ngOnInit(): void {
    this.dataService.challenge().subscribe(
      next => {
        this.challenge = next;
      },
      error => {
        console.log("Can't get the challenge", error);
      }
    )
  }

  onSubmit() {
    this.formAttempt.factorA = this.challenge.factorA;
    this.formAttempt.factorB = this.challenge.factorB;
    console.log('sending attempt: ', this.formAttempt);
    this.dataService.sendGuess(this.formAttempt).subscribe(
      attemptResult => {
        console.log('Received result ', attemptResult);
        console.log('Guess is ', attemptResult.correct);
        this.message = `Your guess is ${attemptResult.correct ? 'correct' : 'wrong'}`;
      },
      error => {
        console.log('Something went wrong: ', error);
      }
    )
  }
}
