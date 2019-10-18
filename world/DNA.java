/**
 * A very simplistic view of DNA. Ignore chromosomes and strands for now. After all
 * it took billions of years for Nature to figure that out!
 */

public class DNA
{
    /**
     * Normally specified per species but every now and then a randome
     * element will creap in to change some of these values.
     */
    
    public enum Behaviours {
	NUMBER_OF_LEGS,  // can be useful in combat or as predator/prey
	NUMBER_OF_ARMS,  // can be useful in combat or as predator/prey
	NUMBER_OF_EYES,  // can be useful in combat or as predator/prey
	NUMBER_OF_HEADS, // should normally be 1!
	NUMBER_OF_HEARTS, // should normally be 1!
	OVERALL_SIZE,  // size
	BASE_TEMPERATURE,  // base needed to live
	TEMPERATURE_THRESHOLD,  // how much less than base it can survive
	LAND_BASED, // does the creature live on the land?
	WATER_BASED, // does the creature live in water/ocean?
	AIR_BASED, // does the creature live in the air?
	BASE_OXYGEN,  // base needed to live
	OXYGEN_THRESHOLD,  // how much less than base it can survive
	DISEASE,  // non-zero means has a disease and aggressiveness
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
	CAN_REPRODUCE, // should usually be non-zero
	FLYING_SPEED // if 0 then can't fly even if have wings
    }

    public int getValue (int v)
    {
	return _theMagic[v];
    }

    public void setValue (int v, int n)
    {
	_theMagic[v] = n;
    }

    private static final int SIZE_OF_MAGIC = 100;
    
    private int[] _theMagic = new int[SIZE_OF_MAGIC];
}
