package com.example.blackjack;

import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        switch (action) {
            case "Deal": {
                Hand playerHand = new Hand();
                Hand dealerHand = new Hand();
                Deck deck = new Deck();

                deck.dealFaceUp(playerHand);
                deck.dealFaceUp(dealerHand);
                deck.dealFaceUp(playerHand);
                deck.dealFaceDown(dealerHand);

                session.setAttribute("playerHand", playerHand);
                session.setAttribute("dealerHand", dealerHand);
                session.setAttribute("deck", deck);

                if (session.getAttribute("money") == null) {
                    session.setAttribute("money", 0);
                }

                int bet = Integer.parseInt(request.getParameter("bet"));
                session.setAttribute("bet", bet);

                break;
            }

            case "Hit": {
                Hand playerHand = (Hand) session.getAttribute("playerHand");
                Deck deck = (Deck) session.getAttribute("deck");
                deck.dealFaceUp(playerHand);

                break;
            }

            case "Stand": {
                Hand playerHand = (Hand) session.getAttribute("playerHand");
                Hand dealerHand = (Hand) session.getAttribute("dealerHand");
                Deck deck = (Deck) session.getAttribute("deck");

                playerHand.stood = true;
                dealerHand.flip();

                while (dealerHand.value() < 17) {
                    deck.dealFaceUp(dealerHand);
                }

                break;
            }
        }

        response.sendRedirect("/");
    }
}
