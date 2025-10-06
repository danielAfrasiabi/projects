//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TA Hiring Program
// Course: CS 300 Fall 2023
//
// Author: Kai Tsimpidis
// Email: tsimpidis@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Daniel Afrasaibi
// Partner Email: dafrasiabi@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Used no outside help from any persons
// Online Sources: Used no online sources for help
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/*
 * A simple program for solving various hiring problems.
 */
public class Hiring {
  /*
   * Given a set of `candidates` that we can hire, a list of candidates we've already hired, and a
   * maximum number of tas to hire, return the set of hires made using a greedy strategy that always
   * chooses the candidate that increases hours covered the most.
   * 
   * @param candidates - the set of available candidates to hire from (excluding those already
   * hired)
   * 
   * @param hired - the list of those currently hired
   * 
   * @param hiresLeft - the maximum number of candidates to hire
   * 
   * @return a list of hired candidates
   */

  public static CandidateList greedyHiring(CandidateList candidates, CandidateList hired,
      int hiresLeft) {
    if (hiresLeft <= 0) {
      return hired;
    }

    else {
      Candidate maxCandidate = candidates.get(0);
      CandidateList candidatesCopy = candidates.deepCopy();

      int maxIndex = 0;
      int hoursAvailable1 = 0;
      int hoursAvailable2 = 0;

      boolean[] completion = new boolean[maxCandidate.getAvailability().length];

      if (hired.size() > 0) {
        for (int i = 0; i < hired.size(); ++i) {
          for (int j = 0; j < completion.length; ++j) {
            if (hired.get(i).isAvailable(j) == true) {
              completion[j] = true;
            }
          }
        }
      }

      for (int i = 1; i < candidates.size(); ++i) {
        for (int j = 0; j < candidates.get(i).getAvailability().length; ++j) {
          if (completion[j] == false) {
            if (candidates.get(i).isAvailable(j) == true) {
              hoursAvailable1 += 1;
            }
          }
        }

        for (int k = 0; k < maxCandidate.getAvailability().length; ++k) {
          if (completion[k] == false) {
            if (maxCandidate.isAvailable(k) == true) {
              hoursAvailable2 += 1;
            }
          }
        }

        if (hoursAvailable1 > hoursAvailable2) {
          maxIndex = i;
          maxCandidate = candidates.get(i);
        }
      }

      hired.add(candidatesCopy.get(maxIndex));
      candidatesCopy.remove(maxIndex);
      --hiresLeft;
      return greedyHiring(candidatesCopy, hired, hiresLeft);
    }
  }

  /**
   * Given a set of `candidates` that we can hire, a list of candidates we've already hired, and a
   * maximum number of tas to hire, return the set of hires that maximizes number of scheduled
   * hours. In this function, we will ignore pay rates.
   * 
   * @param candidates - the set of available candidates to hire from (excluding those already
   *                   hired)
   * @param hired      - the list of those currently hired
   * @param hiresLeft  - the maximum number of candidates to hire
   * @return a list of hired candidates
   */
  public static CandidateList optimalHiring(CandidateList candidates, CandidateList hired,
      int hiresLeft) {
    ArrayList<CandidateList> listOfPossibilities = new ArrayList<CandidateList>();

    optimalHiringHelper(listOfPossibilities, candidates, hired, hiresLeft);

    int maxIndex = 0;
    for (int i = 1; i < listOfPossibilities.size(); i++) {
      if (listOfPossibilities.get(i).numCoveredHours() > listOfPossibilities.get(maxIndex)
          .numCoveredHours()) {
        maxIndex = i;
      }
    }

    return listOfPossibilities.get(maxIndex);
  }

  /**
   * Recursive helper method of optimal hiring which takes in a ArrayList and adds all the different
   * combinations of TA's to hire to the listOfPossibilities
   * 
   * @param listOfPossibilities - the list of all different combinations of TA's
   * @param candidates          - the set of available candidates to hire from (excluding those
   *                            already hired)
   * @param hired               - the set of candidates already hired
   * @param hiresLeft           - the amount of TA's wanting to hire per combination
   */
  private static void optimalHiringHelper(ArrayList<CandidateList> listOfPossibilities,
      CandidateList candidates, CandidateList hired, int hiresLeft) {
    Candidate tmpCandidate;

    if (hiresLeft <= 0) {
      listOfPossibilities.add(hired.deepCopy());
    }

    else {
      for (int i = 0; i < candidates.size(); ++i) {
        tmpCandidate = candidates.get(i);
        candidates.remove(i);
        hired.add(tmpCandidate);

        optimalHiringHelper(listOfPossibilities, candidates, hired, hiresLeft - 1);

        candidates.add(i, tmpCandidate);
        hired.remove(hired.size() - 1);
      }
    }
  }

  /**
   * Knapsack dual problem: find the minimum-budget set of hires to achieve a threshold number of
   * hours. That is, given a set of candidates, a set of already-hired candidates, and a minimum
   * number of hours we want covered, what is the cheapest set of candidates we can hire that cover
   * at least that minimum number of hours specified.
   * 
   * @param candidates - the set of available candidates to hire from (excluding those already
   *                   hired)
   * @param hired      - the set of candidates already hired
   * @param minHours   - the minimum number of hours we want to cover total
   * @return a list of hired candidates or null if no set of candidates achieves the requested
   *         number of hours
   */
  public static CandidateList minCoverageHiring(CandidateList candidates, CandidateList hired,
      int minHours) {
    ArrayList<CandidateList> listOfPossibilities = new ArrayList<CandidateList>();

    minCoverageHiringHelper(listOfPossibilities, candidates, hired, minHours);

    if (listOfPossibilities.size() == 0) {
      return null;
    }

    int minCostIndex = 0;

    for (int i = 1; i < listOfPossibilities.size(); i++) {
      if (listOfPossibilities.get(i).totalCost() < listOfPossibilities.get(minCostIndex)
          .totalCost()) {
        minCostIndex = i;
      }
    }
    return listOfPossibilities.get(minCostIndex);

  }

  /**
   * Recursive helper method of minCoverageHiring which takes in a ArrayList and adds all the
   * different combinations of TA's to hire to the listOfPossibilities and stops making combinations
   * once the required minimum hours are fulfilled
   * 
   * @param listOfPossibilities - the list of all different combinations of TA's
   * @param candidates          - the set of available candidates to hire from (excluding those
   *                            already hired)
   * @param hired               - the set of candidates already hired
   * @param minHours            - the minimum number of hours we want to cover total
   */
  private static void minCoverageHiringHelper(ArrayList<CandidateList> listOfPossibilities,
      CandidateList candidates, CandidateList hired, int minHours) {
    Candidate tmpCandidate;

    if (minHours <= 0) {
      listOfPossibilities.add(hired.deepCopy());
    }

    else {
      for (int i = 0; i < candidates.size(); ++i) {
        tmpCandidate = candidates.get(i);
        candidates.remove(i);
        hired.add(tmpCandidate);
        int hoursTaken = hired.numCoveredHours();
        minHours = minHours - hoursTaken;
        optimalHiringHelper(listOfPossibilities, candidates, hired, minHours);

        minHours += hoursTaken;
        candidates.add(i, tmpCandidate);
        hired.remove(hired.size() - 1);
      }
    }
  }
}
