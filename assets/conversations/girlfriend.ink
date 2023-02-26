EXTERNAL getItemCount(item_type)
EXTERNAL getFlag(flag_name)

{
    - getFlag("RING_RETRIEVED_FOR_GIRLFRIEND"):
        -> thanks
    - getFlag("PUZZLE_PIECE_RETRIEVED_FOR_GIRLFRIEND") and getItemCount("RING") > 0:
        -> ring_retrieved
    - getFlag("PUZZLE_PIECE_RETRIEVED_FOR_GIRLFRIEND"):
        -> ring_request
    - getFlag("SMARTPHONE_RETRIEVED_FOR_GIRLFRIEND") and getItemCount("JIGSAW_PUZZLE_PIECE") > 0:
        -> jigsaw_piece_retrieved
    - getFlag("SMARTPHONE_RETRIEVED_FOR_GIRLFRIEND"):
        -> jigsaw_piece_request
    - getItemCount("SMARTPHONE") > 0:
        -> smartphone_retrieved
    - else:
        -> smartphone_request
}

=== thanks ===
Sweetheart: OMG, thanks for all your help, bestie!
Sweetheart: You and I are like, BFFs now.
Sweetheart: If you ever need anything, just, like, call, yeah?
    -> END
    
=== ring_request ===
- Sweetheart: Oh, what am I to do?
* [What's wrong?]
* [Are you alright?]
- Sweetheart: Oh, it's a shambles, honestly.
Sweetheart: I lost the expensive ring I was going to use to propose to my girlfriend
Sweetheart: We were going to sail away into the sunset and spend the rest of our lives together.
Sweetheart: Now I'm going to have to save up all over again...
    -> END

=== ring_retrieved ===
Sweetheart: Is that what I think it is?
Sweetheart: OMG!!! Where did you find it???
Sweetheart: I'm saved...
Sweetheart: Now me and my partner can get all like, married and stuff?
Sweetheart: I have butterflies in my tummy just thinking about it...
Sweetheart: Thank you so much!
REMOVE_ITEM RING 1
SET_FLAG RING_RETRIEVED_FOR_GIRLFRIEND true
    -> END

=== jigsaw_piece_request ===
Sweetheart: Thanks for sorting out my phone!
Sweetheart: Turns out the missed calls were from my partner-in-crime, my be-all-and-end-all, love of my life.
Sweetheart: She's at home today, doing a jigsaw.
Sweetheart: Turns out, it's missing the last piece!
Sweetheart: She got it when she was out shopping down this way earlier last week.
Sweetheart: She thinks it might have fallen in the river.
Sweetheart: Any chances you could help us out?
    -> END
    
=== jigsaw_piece_retrieved ===
Sweetheart: That's it!!! That's the piece!
Sweetheart: OMG, thank you so much, that's made my week
REMOVE_ITEM JIGSAW_PUZZLE_PIECE 1
SET_FLAG PUZZLE_PIECE_RETRIEVED_FOR_GIRLFRIEND true
    -> END

=== smartphone_request ===
- Sweetheart: Oh, what am I to do, what am I to do?
* [What's wrong?]
* [Are you alright?]
- Sweetheart: I'm totally lost!
Sweetheart: There's no way I'm finding my way home.
Sweetheart: Not without my phone.
Sweetheart: I made a little oopsy and it's gone and sunk in the water.
Sweetheart: Even if I was able to fish it out, it's going to take like, 700 days in rice before my baby is ever going to think about working again.
Sweetheart: I don't know what to do!
    -> END
    
=== smartphone_retrieved ===
She shakes the water out of her phone
The screen lights up
Sweetheart: Seems like it works, tysm!
Sweetheart: Oh no, I have like, 300 missed calls...
Sweetheart: I am _so_ in trouble.
REMOVE_ITEM SMARTPHONE 1
SET_FLAG SMARTPHONE_RETRIEVED_FOR_GIRLFRIEND true
    -> END
