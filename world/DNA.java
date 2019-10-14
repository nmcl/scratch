/**
 * A very simplistic view of DNA. Ignore chromosomes and strands for now. After all
 * it took billions of years for Nature to figure that out!
 */

public class DNA
{
    public enum Behaviours {
	NUMBER_OF_LEGS,  // can be useful in combat or as predator/prey
	NUMBER_OF_ARMS,  // can be useful in combat or as predator/prey
	NUMBER_OF_EYES,  // can be useful in combat or as predator/prey
	NUMBER_OF_HEADS, // should normally be 1!
	OVERALL_SIZE,  // size
	BASE_TEMPERATURE,  // base needed to live
	TEMPERATURE_THRESHOLD,  // how much less than base it can survive
	BASE_OXYGEN,  // base needed to live
	OXYGEN_THRESHOLD,  // how much less than base it can survive
	CANCER,  // non-zero means has it and aggressiveness
	LIFESPAN,  // in units of the world
	MALE,  // non-zero means yes
	FEMALE,  // non-zero means yes
	TOUGHNESS,  // general toughness
	TEETH,  // combat and ability to feed
	CLAWS,  // combat and ability to feed
	HORNS,  // combat
	ARMOUR,  // gives protection against cold and combat
	HAIR,  // gives some protection against cold
	WINGS,  // assume even number needed to fly
	FLYING_SPEED // if 0 then can't fly even if have wings
    }
}
