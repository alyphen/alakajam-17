EXTERNAL getItemCount(item_type)
EXTERNAL getFlag(flag_name)

{
    - getFlag("CHALICE_RETRIEVED_FOR_PIRATE") and getFlag("RING_RETRIEVED_FOR_PIRATE") and getFlag("GEMS_RETRIEVED_FOR_PIRATE") and getFlag("TREASURE_CHEST_RETRIEVED_FOR_PIRATE") and getFlag("GOOD_ORB_RETRIEVED_FOR_PIRATE") and getFlag("BAD_ORB_RETRIEVED_FOR_PIRATE") and getFlag("STATIC_ORB_RETRIEVED_FOR_PIRATE") and getFlag("CROWN_RETRIEVED_FOR_PIRATE"):
        -> rich
    - getItemCount("CHALICE") > 0 or getItemCount("RING") > 0 or getItemCount("GEM") > 0 or getItemCount("TREASURE_CHEST") > 0 or getItemCount("GOOD_ORB") > 0 or getItemCount("BAD_ORB") > 0 or getItemCount("STATIC_ORB") > 0 or getItemCount("CROWN") > 0:
        -> hand_item
    - else:
        -> fetch_booty
}

=== rich ===
Pirate: Yar har har and shiver me timbers, we be drownin' in booty mate.
Pirate: Yer be the best first mate a captain like me could ever wish for
Pirate: Yer be welcome in me crew any time ye wish
    -> END
    
=== hand_item ===
Pirate: Yo ho ho, what do we have here?
Pirate: Yer discovered some booty?
Pirate: Let's 'ave a look at it, me hearty.
* { getItemCount("CHALICE") > 0 } [Chalice] -> hand_chalice
* { getItemCount("RING") > 0 } [Ring] -> hand_ring
* { getItemCount("GEM") > 0 } [Gem] -> hand_gem
* { getItemCount("TREASURE_CHEST") > 0 } [Treasure chest] -> hand_treasure_chest
* { getItemCount("GOOD_ORB") > 0 } [Orb with a bright ring] -> hand_good_orb
* { getItemCount("BAD_ORB") > 0 } [Orb with red eyes] -> hand_bad_orb
* { getItemCount("STATIC_ORB") > 0 } [Orb with static noise] -> hand_static_orb
* { getItemCount("CROWN") > 0 } [Crown] -> hand_crown
* [Nothing] -> hand_nothing

=== hand_chalice ===
Pirate: Yar har, this be a chalice.
Pirate: Fit fer a king... or a pirate.
Pirate: Thanks, matey.
Pirate: Ye will be handsomely rewarded for this one in me crew.
REMOVE_ITEM CHALICE 1
SET_FLAG CHALICE_RETRIEVED_FOR_PIRATE true
    -> END

=== hand_ring ===
Pirate: Yar har, a ring?
Pirate: Yarr, this be pretty fine.
Pirate: Ye be promoted to first mate for this one fer sure.
Pirate: Thanks, matey.
REMOVE_ITEM RING 1
SET_FLAG RING_RETRIEVED_FOR_PIRATE true
    -> END

=== hand_gem ===
Pirate: Yar, a gem?
Pirate: Where ye be findin' this one?
Pirate: These be worth their weight.
Pirate: Thanks, mate.
REMOVE_ITEM GEM 1
SET_FLAG GEM_RETRIEVED_FOR_PIRATE true
    -> END

=== hand_treasure_chest ===
Pirate: YAR! An entire chest?
Pirate: Ye know, come to think it, me thinks we dropped one o' these when sailing down this way.
Pirate: Thanks for gettin' it. We be rich, mate!
REMOVE_ITEM TREASURE_CHEST 1
SET_FLAG TREASURE_CHEST_RETRIEVED_FOR_PIRATE true
    -> END

=== hand_good_orb ===
Pirate: Yar, what's this?
Pirate: An orb?
The pirate stares into the orb.
Pirate: It be lookin' kind o' pretty, really.
Pirate: Might stick this one on me shelf.
Pirate: Thanks muchly.
REMOVE_ITEM GOOD_ORB 1
SET_FLAG GOOD_ORB_RETRIEVED_FOR_PIRATE true
    -> END

=== hand_bad_orb ===
Pirate: Yar, what's this?
Pirate: An orb?
Pirate: It be lookin' kind o' spooky.
The pirate stares into the orb.
Pirate: Well, as spooky as it be, it'll sure fetch a pretty penny for the right bidder.
Pirate: Thanks, mate.
REMOVE_ITEM BAD_ORB 1
SET_FLAG BAD_ORB_RETRIEVED_FOR_PIRATE true
    -> END
    
=== hand_static_orb ===
Pirate: Yar, what's this?
Pirate: An orb?
Pirate: It be lookin' kind o' funny.
The pirate shakes the orb.
Pirate: Well, it be sure as salt there be someone who wants this kind o' thing.
Pirate: Thanks for yer efforts, chum.
REMOVE_ITEM STATIC_ORB 1
SET_FLAG STATIC_ORB_RETRIEVED_FOR_PIRATE true
    -> END
    
=== hand_crown ===
Pirate: Yar, what's this?
Pirate: A CROWN?
Pirate: This be lookin' mighty fine.
Pirate: Not as fine as me hat, mind.
Pirate: But pretty fine nonetheless.
Pirate: There sure be some landlubber who might want somethin' like this.
REMOVE_ITEM CROWN 1
SET_FLAG CROWN_RETRIEVED_FOR_PIRATE true
    -> END

=== hand_nothing ===
Pirate: Yar, nothing, eh?
Pirate: Well, come back to me when ye got somethin' good, ye hear?
    -> END

=== fetch_booty ===
Pirate: Yar har har, the legends tell me there be treasure here in this here river.
Pirate: Me map marks this spot with a nice big red X, it be clear as the north star in the night sky.
Pirate: If ye find anything of value, come back to me and ye'll be... handsomely rewarded.
    -> END
