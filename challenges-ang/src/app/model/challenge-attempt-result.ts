import {User} from "./user";

export class ChallengeAttemptResult {

  constructor(public user: User, public factorA: number, public factorB: number, public guess: number, public correct: boolean) {
  }
}
