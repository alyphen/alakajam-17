EXTERNAL getItemCount(item_type)
EXTERNAL getFlag(flag_name)

{
    - getFlag("GOOD_ORB_RETRIEVED_FOR_WIZARD"):
        -> good_orb_thanks
    - getFlag("BAD_ORB_RETRIEVED_FOR_WIZARD"):
        -> bad_orb_bad
    - getFlag("STATIC_ORB_RETRIEVED_FOR_WIZARD"):
        -> static_orb_ponder
    - getItemCount("GOOD_ORB") > 0 or getItemCount("BAD_ORB") > 0 or getItemCount("STATIC_ORB") > 0:
        -> orbs_retrieved
    - else:
        -> orb_request
}

=== good_orb_thanks ===
Wizard: I see great things in your future...
Wizard: Yes....
Wizard: Yes..........
    -> END

=== bad_orb_bad ===
Wizard: The world's ending is upon us...
Wizard: The calamity will come...
Wizard: What can we do?
    -> END

=== static_orb_ponder ===
Wizard: I wonder what this could mean?
    -> END

=== orbs_retrieved ===
Wizard: Ohoho! You have the orb for me?
* { getItemCount("GOOD_ORB") > 0 } [The orb with the shining ring] -> hand_good_orb
* { getItemCount("BAD_ORB") > 0 } [The orb with the pair of red eyes] -> hand_bad_orb
* { getItemCount("STATIC_ORB") > 0 } [The orb with the static noise] -> hand_static_orb
    -> END

=== hand_good_orb ===
Wizard: Oho, what's this?
Wizard: Ahh. Hmm.
Wizard: I see... great things!
Wizard: Greatness!
Wizard: Young one... your future is great....
Wizard: That, I am sure of...
REMOVE_ITEM GOOD_ORB 1
SET_FLAG GOOD_ORB_RETRIEVED_FOR_WIZARD true
    -> END
    
=== hand_bad_orb ===
Wizard: Ahh, what's this?
Wizard: Oh. Hm.
Wizard: Ahh.
Wizard: Well, that's a problem.
Wizard: That doesn't bode well at all.
Wizard: Hmm.
Wizard: It looks like the world might end in a few days.
Wizard: I very well hope that doesn't put a stopper in any plans you might have had.
REMOVE_ITEM BAD_ORB 1
SET_FLAG BAD_ORB_RETRIEVED_FOR_WIZARD true
    -> END
    
=== hand_static_orb ===
Wizard: Oh, and what's this then?
The wizard ponders the orb.
Wizard: Is this thing working?
The wizard shakes the orb.
Wizard: Does it have signal?
The wizard knocks on the orb.
Wizard: I don't think this thing is working properly.
Wizard: I thank you for going to the effort of fetching it, though.
REMOVE_ITEM STATIC_ORB 1
SET_FLAG GOOD_ORB_RETRIEVED_FOR_WIZARD true
    -> END
    
=== orb_request ===
Wizard: Ah. Hello there.
Wizard: I was just pondering my orb in my tower, when it rolled off the pedestal and down the hill.
Wizard: I chased it all the way, but before I could catch it, it seems to have fallen in the river.
Wizard: Now, I don't want to get my robe all wet, but you will get my eternal gratitude if you fetch it for me.
Wizard: Tell you what, if you can fetch my orb, I'll tell your fortune.
    -> END
