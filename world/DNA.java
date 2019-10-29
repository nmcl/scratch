/**
 * A very simplistic view of DNA. Ignore chromosomes and strands for now. After all
 * it took billions of years for Nature to figure that out!
 *
 * There are two ways in which DNA can change:
 * - through a Mutator when two instances of the creature mate.
 * - randomly through the lifetime of a creature.
 *
 * Each time DNA changes it may result in a creature dying, or having a more
 * limited lifespan. Alternatively it may result in the creature having a better
 * chance of surviving.
 */

/**
 * Each element of DNA has a range of values. And a chance of mutation.
 * Does the chance change as the value changes?
 *
 * Think of it as a spectrum.
 * - minor range at far left which is bad.
 * - major range at far right which is bad.
 * - average values/range.
 * - range between minor and average which is positive somehow?
 * - range between average and major which is positive somehow?
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
	OVERALL_SIZE,  // size between 1 and 100
	BASE_TEMPERATURE,  // base needed to live
	TEMPERATURE_THRESHOLD,  // how much less than base it can survive
	TERRESTRIAL, // does the creature live on the land?
	AQUATIC, // does the creature live in water/ocean?
	AVIAN, // does the creature live in the air?
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

    @Override
    public boolean equals (Object dna)
    {
	if (this == dna)
	    return true;

	if (dna == null || dna.getClass() != this.getClass()) 
            return false;
    
	for (int i = 0; i < SIZE_OF_DNA; i++)
	{
	    if (_theMagic[i] != dna.get(i))
		return false;
	}

	return true;
    }
    
    public int getValue (int v)
    {
	return _theMagic[v];
    }

    public void setValue (int v, int n)
    {
	_theMagic[v] = n;
    }

    private static final int SIZE_OF_DNA = 100;
    
    private int[] _theMagic = new int[SIZE_OF_MAGIC];
}
