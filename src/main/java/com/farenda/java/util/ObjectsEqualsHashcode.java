package com.farenda.java.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class ObjectsEqualsHashcode {

    public static class Player {
        public final String login;

        public Player(String login) {
            this.login = login;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Player player = (Player) o;
            // accepts nulls:
            return Objects.equals(login, player.login);
        }

        @Override
        public int hashCode() {
            // accepts nulls:
            return Objects.hash(login);
        }

        @Override
        public String toString() {
            return "Player{login='" + login + "'}";
        }
    }

    public static void main(String[] args) {
        Collection<Player> players = Arrays.asList(
                new Player(null),
                new Player("player1"),
                new Player("player2"));
        System.out.println("Players: " + players);

        Collection<Player> sortedPlayers = new HashSet<>(players);
        System.out.println("In Set: " + sortedPlayers);

        // will not throw NullPointerException:
        sortedPlayers.contains(new Player(null));
    }
}
