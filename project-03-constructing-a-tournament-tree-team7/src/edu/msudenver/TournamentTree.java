package edu.msudenver;

import com.pearson.tree.Heap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TournamentTree {
    protected Heap<Integer, Double> tournamentHeap;

    protected ArrayList<Integer> teamPositions;

    protected String inputFileName;

    public TournamentTree(String inputFileName) {
        this.tournamentHeap = new Heap<>();
        this.teamPositions = new ArrayList<>();
        this.inputFileName = inputFileName;
    }

    public List<Integer> readTeamsFromFile() throws IOException {
        // TODO implement


        List<Integer> teams = new ArrayList<Integer>();
        File file = new File(inputFileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            teams.add(Integer.parseInt(line));
        }

        return teams;
    }


    public void setup() throws IOException {
        // TODO implement

        // This method needs to read the list of 64 integers using
        // readTeamsFromFile, and then add each team as a leaf node
        // to the heap, in the same order they are specified in the file.

        List<Integer> teams = readTeamsFromFile();
        int i = 0;
        for (int team : teams) {
            tournamentHeap.insert(team, (double) team);
            teamPositions.add(i);
            i++;
        }
        int childIndex = 0;
        for (int j = 0; j < 6; j++) {
            childIndex = tournamentHeap.leftChildIndex(childIndex);
        }
        tournamentHeap.insert(0, (double) 0);
        teamPositions.add(childIndex);
        childIndex = 0;
        for (int j = 0; j < 6; j++) {
            childIndex = tournamentHeap.leftChildIndex(childIndex);
        }
        tournamentHeap.insert(0, (double) 0);
        teamPositions.add(childIndex);
        tournamentHeap.displayHeap();


        }


        public int playTournament () {
            // TODO implement


       //used to help start the tournament by starting at the bottom of the heap, however wasnt able to get it to work..
            int parentIndex = 0;
            int leftChildIndex = 0;
            int rightChildIndex = 0;
            int leftChildKey = 0;
            int rightChildKey = 0;
            int parentKey = 0;
            int winner = 0;
            int winnerIndex = 0;

            for (int i = 0; i < 6; i++) {
                // get the parent of the two teams playing
                parentIndex = tournamentHeap.parentIndex(teamPositions.get(i));
                // get the left child
                leftChildIndex = tournamentHeap.leftChildIndex(parentIndex);
                // get the right child
                rightChildIndex = tournamentHeap.rightChildIndex(parentIndex);
                // get the left child key
                leftChildKey = tournamentHeap.elementAt(leftChildIndex).getKey();
                // get the right child key
                rightChildKey = tournamentHeap.elementAt(rightChildIndex).getKey();
                // get the parent key
                parentKey = tournamentHeap.elementAt(parentIndex).getKey();
                // compare the two teams
                if (leftChildKey > rightChildKey) {
                    // Whichever value is biggest is the team that "wins" the game
                    winner = leftChildKey;
                    winnerIndex = leftChildIndex;
                } else {
                    winner = rightChildKey;
                    winnerIndex = rightChildIndex;
                }
                // The winner's value becomes the new value for the parent, signaling
                // that they won the game
                tournamentHeap.elementAt(parentIndex).setKey(winner);
                teamPositions.set(i, winnerIndex);
            }
            tournamentHeap.displayHeap();
            return winner;



        }

        public static void main (String[]args) {

        }
    }
