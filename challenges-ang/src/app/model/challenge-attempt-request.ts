export class ChallengeAttemptRequest {

  constructor(public userAlias: string, public factorA: number, public factorB: number, public guess: number) {
  }
}
