/**
 * @author Gregor Semmler <semmlerg@in.tum.de>
*/
package org.shalim.restaurantfinder.model;

/**
 * Categorizes the conflict modes of a given {@link ParticipantPersonlity} into high, medium, or low. This is calculated based
 * on a representative sample. If the value for a mode is above the 75 percentile it is high and it is low if it is
 * below the 25 percentile. Otherwise it is medium.
 */
class TKIModeCategorizer {

    /**
     * Sample of 6168 individuals from 16 countries who took the TKI Personality test, see:
     * www.kilmanndiagnostics.com/sites/default/kilmann-private-10020393424lksdjfksdjfl093u/TKI_International_Brief.pdf
     */
    public static final TKIModeCategorizer INTERNATIONAL = new TKIModeCategorizer(7, 2, 8, 4, 10, 5, 8, 4, 7, 3);


    /**
     * Sample of 8000 individuals from the USA, see:
     * www.kilmanndiagnostics.com/sites/default/files/TKI_Technical_Brief.pdf
     */
    public static final TKIModeCategorizer US = new TKIModeCategorizer(7, 2, 9, 4, 10, 5, 8, 4, 7, 3);
    private int competingHigh;
    private int competingLow;
    private int collaboratingHigh;
    private int collaboratingLow;
    private int compromosingHigh;
    private int compromosingLow;
    private int avoidingHigh;
    private int avoidingLow;
    private int accommodatingHigh;
    private int accommodatingLow;

    TKIModeCategorizer(int competingHigh, int competingLow, int collaboratingHigh, int collaboratingLow,
                       int compromosingHigh, int compromosingLow, int avoidingHigh, int avoidingLow,
                       int accommodatingHigh, int accommodatingLow) {
        this.competingHigh = competingHigh;
        this.competingLow = competingLow;
        this.collaboratingHigh = collaboratingHigh;
        this.collaboratingLow = collaboratingLow;
        this.compromosingHigh = compromosingHigh;
        this.compromosingLow = compromosingLow;
        this.avoidingHigh = avoidingHigh;
        this.avoidingLow = avoidingLow;
        this.accommodatingHigh = accommodatingHigh;
        this.accommodatingLow = accommodatingLow;
    }

    boolean hasHighCompeting(ParticipantPersonality personality) {
        return personality.competingScore >= competingHigh;
    }

    boolean hasLowCompeting(ParticipantPersonality personality) {
        return personality.competingScore <= competingLow;
    }

    boolean hasHighCollaborating(ParticipantPersonality personality) {
        return personality.cooperatingScore >= collaboratingHigh;
    }

    boolean hasLowCollaborating(ParticipantPersonality personality) {
        return personality.cooperatingScore <= collaboratingLow;
    }

    boolean hasHighCompromising(ParticipantPersonality personality) {
        return personality.compromisingScore >= compromosingHigh;
    }

    boolean hasLowCompromising(ParticipantPersonality personality) {
        return personality.compromisingScore <= compromosingLow;
    }

    boolean hasHighAvoiding(ParticipantPersonality personality) {
        return personality.avoidingScore >= avoidingHigh;
    }

    boolean hasLowAvoiding(ParticipantPersonality personality) {
        return personality.avoidingScore <= avoidingLow;
    }

    boolean hasHighAccommodating(ParticipantPersonality personality) {
        return personality.accommodatingScore >= accommodatingHigh;
    }

    boolean hasLowAccommodating(ParticipantPersonality personality) {
        return personality.accommodatingScore <= accommodatingLow;
    }
}

