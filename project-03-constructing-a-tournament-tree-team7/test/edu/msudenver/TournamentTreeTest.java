package edu.msudenver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class TournamentTreeTest {
    private TournamentTree tournamentTree;

    @BeforeEach
    void setupTest() {
        tournamentTree = new TournamentTree("sampleTeams.txt");
    }

    @Test
    void testReadTeamsFromFile() throws IOException {
        List<Integer> teams = tournamentTree.readTeamsFromFile();

        Assertions.assertEquals(64, teams.size(), "There should be 64 teams");

        Assertions.assertEquals(433, teams.get(0), "The first team should be 433");
        Assertions.assertEquals(972, teams.get(35), "The thirty-sixth team should be 972");
        Assertions.assertEquals(424, teams.get(63), "The last team should be 424");
    }

    @Test
    void testSetup() throws IOException {
        tournamentTree.setup();
        List<Integer> teams = tournamentTree.readTeamsFromFile();

        int childIndex = 0;
        for(int i = 0; i < 6; i++) {
            childIndex = tournamentTree.tournamentHeap.leftChildIndex(childIndex);
        }
        Assertions.assertEquals(teams.get(0), tournamentTree.tournamentHeap.elementAt(childIndex).getKey(),
                "Position " + childIndex + " in heap should equal team number " + teams.get(0));

        childIndex = 0;
        for(int i = 0; i < 6; i++) {
            childIndex = tournamentTree.tournamentHeap.rightChildIndex(childIndex);
        }
        Assertions.assertEquals(teams.get(teams.size()-1), tournamentTree.tournamentHeap.elementAt(childIndex).getKey(),
                "Position " + childIndex + " in heap should equal team number " + teams.get(teams.size()-1));
    }

    @Test
    void testPlayTournament() throws IOException {
        tournamentTree.setup();
        int winner = tournamentTree.playTournament();

        tournamentTree.tournamentHeap.displayHeap();

        Assertions.assertEquals(972, winner, "Winner should be 972");

        Assertions.assertEquals(972, tournamentTree.tournamentHeap.elementAt(0).getKey(),
                "Root node of heap should be 972");

        Assertions.assertEquals(912, tournamentTree.tournamentHeap.elementAt(1).getKey(),
                "Root->left child should be 912");
        Assertions.assertEquals(912, tournamentTree.tournamentHeap.elementAt(3).getKey(),
                "Root->left->left child should be 912");
        Assertions.assertEquals(694, tournamentTree.tournamentHeap.elementAt(4).getKey(),
                "Root->left->right should be 694");

        Assertions.assertEquals(972, tournamentTree.tournamentHeap.elementAt(2).getKey(),
                "Root->right child should be 972");
        Assertions.assertEquals(972, tournamentTree.tournamentHeap.elementAt(5).getKey(),
                "Root-right->left child should be 972");
        Assertions.assertEquals(938, tournamentTree.tournamentHeap.elementAt(6).getKey(),
                "Root->right->right child should be 938");
    }
}
