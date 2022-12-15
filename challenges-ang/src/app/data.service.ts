import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {Observable} from "rxjs";
import {Challenge} from "./model/challenge";
import {ChallengeAttemptRequest} from "./model/challenge-attempt-request";
import {ChallengeAttemptResult} from "./model/challenge-attempt-result";

@Injectable({
  providedIn: 'root'
})
export class DataService {
  readonly getChallenge = environment.restUrl + '/challenges/random';
  readonly postResult = environment.restUrl + '/attempts'
  readonly getAttemptsByAlias = environment.restUrl + '/attempts?alias='

  constructor(private http: HttpClient) { }

  public challenge(): Observable<Challenge> {
    return this.http.get<Challenge>(this.getChallenge);
  }

  public sendGuess(attempt: ChallengeAttemptRequest): Observable<ChallengeAttemptResult> {
    return this.http.post<ChallengeAttemptResult>(this.postResult, attempt);
  }

  public getAttempts(userAlias: string): Observable<ChallengeAttemptResult[]> {
    return this.http.get<ChallengeAttemptResult[]>(this.getAttemptsByAlias + userAlias);
  }
}
