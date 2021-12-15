package fitpay.engtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
* Model to represent a CreditCards object to match JSON object structure
*/
public class CreditCards {
    @JsonProperty("results")
    private List<CreditCard> cards;

    public List<CreditCard> getCreditCards()
    {
        return cards;
    }

    public void setCreditCards(List<CreditCard> cards)
    {
        this.cards = cards;
    }

}
