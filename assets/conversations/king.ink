EXTERNAL getItemCount(item_type)
EXTERNAL getFlag(flag_name)

{
    - getFlag("CROWN_RETRIEVED_FOR_KING"):
        -> thanks
    - getFlag("CHALICE_RETRIEVED_FOR_KING") and getItemCount("CROWN") > 0:
        -> crown_retrieved
    - getFlag("CHALICE_RETRIEVED_FOR_KING"):
        -> crown_request
    - getFlag("CANDLE_HOLDER_RETRIEVED_FOR_KING") and getItemCount("CHALICE") > 0:
        -> chalice_retrieved
    - getFlag("CANDLE_HOLDER_RETRIEVED_FOR_KING"):
        -> chalice_request
    - getItemCount("CANDLE_STAND") > 0:
        -> candle_holder_retrieved
    - else:
        -> candle_holder_request
}

=== thanks ===
King: Thank you for all of your help, brave knight.
    -> END
    
=== grumpy ===
King: You dare talk to me after supporting dastardly pirates over your own King and country?
King: What will I be without a crown?
King: You have ruined the entire faire.
    -> END
    
=== candle_holder_request ===
King: We're holding a banquet at the faire and you know what would make it look really authentic?
King: A Medieval-style candle holder on the table.
King: There used to be a prosperous village here in the Medieval era, you know.
King: I'm sure you could find all sorts of things down there.
    -> END
    
=== candle_holder_retrieved ===
King: Beautiful! Superb! Wonderful!
King: With a little polishing, that will do just fine.
REMOVE_ITEM CANDLE_STAND 1
SET_FLAG CANDLE_HOLDER_RETRIEVED_FOR_KING true
    -> END

=== chalice_request ===
King: I'm imagining the banquet now, and...
King: Ah, yes, I can see myself at the top of the table.
King: I'm welcoming everyone to the feast.
King: I raise my chalice....
King: CHALICE!
King: I need a chalice, peasant.
King: Please get me one before the banquet.
* [Of course, my liege]
    King: May light rest upon your shoulders, go forth with honor.
    -> END
* [Not with that attitude]
    King: Please? It won't be the same without one.
    -> END

=== chalice_retrieved ===
King: A fine chalice indeed.
King: Fit for a king, one might say.
He laughs heartily.
King: Now the banquet may begin with a splendourous display of wealth...
REMOVE_ITEM CHALICE 1
SET_FLAG CHALICE_RETRIEVED_FOR_KING true
    -> END
    
=== crown_request ===
{ getFlag("CROWN_RETRIEVED_FOR_PIRATE"):
        -> crown_given_to_pirate
    - else:
        -> ask_for_crown
}

=== crown_given_to_pirate ===
King: You WHAT?
King: You dare give MY crown to such a... a SCOUNDREL?
King: What did you do that for?
King: A scallywag! A sewer rat!
King: I clearly can't trust you with anything.
King: Don't even speak to me.
    -> END
    
=== ask_for_crown ===
King: You know, there's one more thing that would be absolutely befitting of an outfit such as mine own.
King: Every real king needs a crown.
King: And as of yet, it seems to be missing.
King: Peasant, please fetch me a crown.
    -> END
    
=== crown_retrieved ===
King: A crown!
King: Splendiforous! Maginificent! Sensational!
King: Thanks to all your help, the faire will be a massive success.
REMOVE_ITEM CROWN 1
SET_FLAG CROWN_RETRIEVED_FOR_KING true
    -> END