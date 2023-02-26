EXTERNAL getItemCount(item_type)
EXTERNAL getFlag(flag_name)

{
    - getFlag("PUZZLE_PIECE_RETRIEVED_FOR_CHILD"):
        -> thanks
    - getItemCount("JIGSAW_PUZZLE_PIECE") > 0:
        -> puzzle_piece_retrieved
    - getFlag("LEGO_BRICK_RETRIEVED_FOR_CHILD"):
        -> puzzle_piece_request
    - getFlag("TEDDY_BEAR_RETRIEVED_FOR_CHILD"):
        -> lego_brick_request
    - getItemCount("HUNGY") > 0:
        -> teddy_bear_retrieved
    - getItemCount("OTHER_TEDDY_1") > 0 || getItemCount("OTHER_TEDDY_2") > 0 || getItemCount("OTHER_TEDDY_3") > 0:
        -> wrong_teddy_bear_retrieved
    - else:
        -> teddy_bear_request
    
}

=== teddy_bear_request ===
Child: I dropped Hungy in the river...
Child: I can't sleep without him.
Child: I've had him since I was thiiis big.
Child: He's much bigger than me and reaaaaally cuddly.
Child: Could you get him for me?
    -> END
    
=== teddy_bear_retrieved ===
Child: Hungy!!!
Child: I thought I'd never see you again.
The child hugs Hungy very tightly.
Child: Thank you very much!
SET_FLAG TEDDY_BEAR_RETRIEVED_FOR_CHILD true
    -> END

=== wrong_teddy_bear_retrieved ===
Child: That's not my Hungy!
Child: Hungy is red...
    -> END

=== lego_brick_request ===
Child: Thank you for getting Hungy back.
Child: I was building a house but I dropped the last brick in the river...
Child: How will I ever finish it now?
    -> END
    
=== lego_brick_retrieved ===
Child: Oh! That's the last piece!
Child: I thought it was lost for sure, and I'd never be able to finish my house!
SET_FLAG LEGO_BRICK_RETRIEVED_FOR_CHILD true
    -> END
    
=== puzzle_piece_request ===
Child: I finished my house now thanks to you.
Child: Then I was doing my jigsaw
Child: But just before I could finish it I dropped the last piece.
Child: Can you get it for me?
    -> END

=== puzzle_piece_retrieved ===
Child: That's the last piece!
Child: Thank you so much!
SET_FLAG PUZZLE_PIECE_RETRIEVED_FOR_CHILD true
    -> END
    
=== thanks ===
Child: Thanks for all your help!
    -> END
