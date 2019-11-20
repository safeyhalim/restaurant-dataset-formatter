package org.shalim.restaurantfinder.model;

public class ParticipantPersonality {
	private int participantId;
	public int competingScore;
	public int cooperatingScore;
	public int avoidingScore;
	public int accommodatingScore;
	public int compromisingScore;
	private String[] answers;
	private double personalityScore;
	private TKIModeCategorizer modeCategorizer = TKIModeCategorizer.INTERNATIONAL;

	public int getParticipantId() {
		return participantId;
	}

	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public double getPersonalityScore() {
		return personalityScore;
	}

	public void setPersonalityScore(double personalityScore) {
		this.personalityScore = personalityScore;
	}

	/**
	 * @author Gregor Semmler <semmlerg@in.tum.de>
	 * 
     * Calculates the Conflict Mode Weight from a given {@link ParticipantPersonality} . Its value should be between 0 and 1, where
     * a smaller value indicates an easygoing personality, a higher value a stronger personality. The calculation is based
     * on the following :
     *
     * Quijano-Sanchez, Lara; Recio-Garcia, Juan A.; Diaz-Agudo, Belen; Jimenez-Diaz, Guillermo (2013): Social Factors
     * in Group Recommender Systems. In: ACM Trans. Intell. Syst. Technol. 4 (1), 8:1‐8:30. DOI: 10.1145/2414425.2414433.
     *
     * Recio-Garcia, Juan A.; Jimenez-Diaz, Guillermo; Sanchez-Ruiz, Antonio A.; Diaz-Agudo, Belen (2009): Personality
     * Aware Recommendations to Groups. In: Proceedings of the Third ACM Conference on Recommender Systems. New York,
     * NY, USA: ACM (RecSys ’09), S. 325–328. Online http://doi.acm.org/10.1145/1639714.1639779.
     *
     * @return the resulting conflictModeWeight as a double value
     */
    public void computeConflictModeWeight() {
    	updateScore();
    	
        double assertiveness = 0.0;
        double cooperativeness = 0.0;

        if(modeCategorizer.hasHighCompeting(this)) {
            assertiveness += 0.375;
            cooperativeness -= 0.15;
        } else if(modeCategorizer.hasLowCompeting(this)){
            assertiveness -= 0.075;
        }

        if(modeCategorizer.hasHighCollaborating(this)) {
            assertiveness += 0.375;
            cooperativeness += 0.375;
        } else if(modeCategorizer.hasLowCollaborating(this)) {
            assertiveness -= 0.075;
            cooperativeness -= 0.075;
        }

        //The compromising value doesn't influence the Conflict Mode Weight

        if(modeCategorizer.hasHighAvoiding(this)) {
            assertiveness -= 0.375;
            cooperativeness -= 0.375;
        } else if(modeCategorizer.hasLowAvoiding(this)) {
            assertiveness += 0.075;
            cooperativeness += 0.075;
        }
        if(modeCategorizer.hasHighAccommodating(this)) {
            assertiveness -= 0.15;
            cooperativeness += 0.375;
        } else if(modeCategorizer.hasLowAccommodating(this)) {
            cooperativeness -= 0.075;
        }

        personalityScore = (1.0 + assertiveness - cooperativeness) / 2;
    }
    
	/**
	 * @author Gregor Semmler <semmlerg@in.tum.de>
	 *
	 *         The ID of the question is its location in the answers array plus 1
	 *         (question number 1's answer is in answers[0])
	 *
	 *         The following questions are used: 1, "I try to find a compromise
	 *         solution", "I attempt to deal with all of his and my concerns" 2,
	 *         "There are times when I let others take responsibility for solving
	 *         the problem", "Rather than negotiate the things on which we disagree,
	 *         I try to stress those things upon which we both agree" 3, "I am
	 *         usually firm in pursuing my goals", "I might try to soothe the
	 *         other's feelings and preserve our relationship" 4, "I try to find a
	 *         compromise solution", "I sometimes sacrifice my own wishes for the
	 *         wishes of the other person" 5, "I feel that the differences are not
	 *         always worth worrying about", "I make some effort to get my way" 6,
	 *         "I am usually firm in pursuing my goals", "I attempt to get all
	 *         concerns and issues immediately out in the open" 7, "I try to
	 *         postpone the issue until I have had some time to think it over", "I
	 *         give up some points in exchange for others" 8, "I try to avoid
	 *         creating unpleasantness for myself", "I try to win my position" 9, "I
	 *         consistently seek the other's help in working out a solution", "I try
	 *         to do what is necessary to avoid useless tensions" 10, "I attempt to
	 *         get all concerns and issues immediately out in the open", "I might
	 *         try to soothe the other's feelings and preserve our relationship" 11,
	 *         "I am firm in pursuing my goals", "I try to find a compromise
	 *         solution" 12, "I sometimes avoid taking positions which could create
	 *         controversy", "I will let him have some of his positions if he lets
	 *         me have some of mine" 13, "I attempt to immediately work through our
	 *         differences", "I try to find a fair combination of gains and losses
	 *         for both of us" 14, "I attempt to get all concerns and issues
	 *         immediately out in the open", "I try to postpone the issue until I
	 *         have had time to think it over" 15, "If it makes the other person
	 *         happy, I might let him maintain his views", "I will let him have some
	 *         of his positions if he lets me have some of mine" 16, "I am usually
	 *         firm in pursuing my goals", "I try to do what is necessary to avoid
	 *         useless tensions" 17, "I try not to hurt the other's feelings", "I
	 *         try to convince the other person of the merits of my position" 18, "I
	 *         might try and soothe the other's feelings and preserve our
	 *         relationship", "I try to do what is necessary to avoid tensions" 19,
	 *         "I tell him my ideas and ask him for his", "I try to show him the
	 *         logic and benefits of my position" 20, "I propose a middle ground",
	 *         "I press to get my points made" 21, "I try not to hurt the other's
	 *         feelings", "I always share the problem with the other person so that
	 *         we can work it out" 22, "I propose a middle ground", "I feel that the
	 *         differences are not always worth worrying about" 23, "I am usually
	 *         firm in pursuing my goals", "I usually seek the other's help in
	 *         working out a solution" 24, "I sometimes avoid taking positions which
	 *         could create controversy", "If it makes the other person happy, I
	 *         might let him maintain his views" 25, "I propose a middle ground", "I
	 *         am nearly always concerned with satisfying all our wishes" 26, "I try
	 *         to show him the logic and benefits of my position", "In approaching
	 *         negotiations, I try to be considerate of the other person's wishes"
	 *         27, "If the other's position seems very important to him, I would try
	 *         to meet his wishes", "I try to get him to settle for a compromise
	 *         solution" 28, "I am very often concerned with satisfying all our
	 *         wishes", "There are times when I let others take responsibility for
	 *         solving the problem" 29, "I try to find a position that is
	 *         intermediate between his and mine", "I assert my wishes" 30, "In
	 *         approaching negotiations, I try to be considerate of the other
	 *         person's wishes", "I always lean toward a direct discussion of the
	 *         problem")
	 */
	private void updateScore() {
		for (int i = 0; i < 30; i++) {
			int questionId = i + 1;
			boolean answerIsA = answers[i].trim().equalsIgnoreCase("A");
			switch (questionId) {
			case 1:
				if (answerIsA)
					compromisingScore++;
				else
					cooperatingScore++;
				break;
			case 2:
				if (answerIsA)
					avoidingScore++;
				else
					accommodatingScore++;
				break;
			case 3:
				if (answerIsA)
					competingScore++;
				else
					accommodatingScore++;
				break;
			case 4:
				if (answerIsA)
					compromisingScore++;
				else
					accommodatingScore++;
				break;
			case 5:
				if (answerIsA)
					avoidingScore++;
				else
					competingScore++;
				break;
			case 6:
				if (answerIsA)
					competingScore++;
				else
					cooperatingScore++;
				break;
			case 7:
				if (answerIsA)
					avoidingScore++;
				else
					compromisingScore++;
				break;
			case 8:
				if (answerIsA)
					avoidingScore++;
				else
					competingScore++;
				break;
			case 9:
				if (answerIsA)
					cooperatingScore++;
				else
					avoidingScore++;
				break;
			case 10:
				if (answerIsA)
					cooperatingScore++;
				else
					accommodatingScore++;
				break;
			case 11:
				if (answerIsA)
					competingScore++;
				else
					compromisingScore++;
				break;
			case 12:
				if (answerIsA)
					avoidingScore++;
				else
					compromisingScore++;
				break;
			case 13:
				if (answerIsA)
					cooperatingScore++;
				else
					compromisingScore++;
				break;
			case 14:
				if (answerIsA)
					cooperatingScore++;
				else
					avoidingScore++;
				break;
			case 15:
				if (answerIsA)
					accommodatingScore++;
				else
					compromisingScore++;
				break;
			case 16:
				if (answerIsA)
					competingScore++;
				else
					avoidingScore++;
				break;
			case 17:
				if (answerIsA)
					accommodatingScore++;
				else
					competingScore++;
				break;
			case 18:
				if (answerIsA)
					accommodatingScore++;
				else
					avoidingScore++;
				break;
			case 19:
				if (answerIsA)
					cooperatingScore++;
				else
					competingScore++;
				break;
			case 20:
				if (answerIsA)
					compromisingScore++;
				else
					competingScore++;
				break;
			case 21:
				if (answerIsA)
					accommodatingScore++;
				else
					cooperatingScore++;
				break;
			case 22:
				if (answerIsA)
					compromisingScore++;
				else
					avoidingScore++;
				break;
			case 23:
				if (answerIsA)
					competingScore++;
				else
					cooperatingScore++;
				break;
			case 24:
				if (answerIsA)
					avoidingScore++;
				else
					accommodatingScore++;
				break;
			case 25:
				if (answerIsA)
					compromisingScore++;
				else
					cooperatingScore++;
				break;
			case 26:
				if (answerIsA)
					competingScore++;
				else
					accommodatingScore++;
				break;
			case 27:
				if (answerIsA)
					accommodatingScore++;
				else
					compromisingScore++;
				break;
			case 28:
				if (answerIsA)
					cooperatingScore++;
				else
					avoidingScore++;
				break;
			case 29:
				if (answerIsA)
					compromisingScore++;
				else
					competingScore++;
				break;
			case 30:
				if (answerIsA)
					accommodatingScore++;
				else
					cooperatingScore++;
				break;
			default:
				System.err.println("Invalid questionId " + questionId);
				return;
			}
		}
	}
}
