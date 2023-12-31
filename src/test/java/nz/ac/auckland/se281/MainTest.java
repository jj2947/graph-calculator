package nz.ac.auckland.se281;

import static nz.ac.auckland.se281.Command.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
  MainTest.Task1.class,
  MainTest.Task2.class, // Uncomment this line when you start Task 2
  MainTest.Task3.class, // Uncomment this line when you start Task 3
  MainTest.YourTests.class, // Uncomment this line to run your own tests
})
public class MainTest {
  public static class Task1 extends CliTest {
    public Task1() {
      super(Main.class);
    }

    @Test
    public void T1_A_roots() throws Exception {
      runCommands(OPEN_FILE, "a.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("[0]");
    }

    @Test
    public void T1_B_roots() throws Exception {
      runCommands(OPEN_FILE, "b.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("[6]");
    }

    @Test
    public void T1_C_roots() throws Exception {
      runCommands(OPEN_FILE, "c.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("[0, 1]");
    }

    @Test
    public void T1_A_reflexivity() throws Exception {
      runCommands(OPEN_FILE, "a.txt", CHECK_REFLEXIVITY);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("The graph is NOT reflexive");
    }

    @Test
    public void T1_B_reflexivity() throws Exception {
      runCommands(OPEN_FILE, "b.txt", CHECK_REFLEXIVITY);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("The graph is NOT reflexive");
    }

    @Test
    public void T1_C_reflexivity() throws Exception {
      runCommands(OPEN_FILE, "c.txt", CHECK_REFLEXIVITY);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("The graph is reflexive");
    }

    @Test
    public void T1_A_symmetry() throws Exception {
      runCommands(OPEN_FILE, "a.txt", CHECK_SYMMETRY);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("The graph is NOT symmetric");
    }

    @Test
    public void T1_B_symmetry() throws Exception {
      runCommands(OPEN_FILE, "b.txt", CHECK_SYMMETRY);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("The graph is NOT symmetric");
    }

    @Test
    public void T1_C_symmetry() throws Exception {
      runCommands(OPEN_FILE, "c.txt", CHECK_SYMMETRY);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("The graph is symmetric");
    }

    @Test
    public void T1_A_transitivity() throws Exception {
      runCommands(OPEN_FILE, "a.txt", CHECK_TRANSITIVITY);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("The graph is NOT transitive");
    }

    @Test
    public void T1_B_transitivity() throws Exception {
      runCommands(OPEN_FILE, "b.txt", CHECK_TRANSITIVITY);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("The graph is NOT transitive");
    }

    @Test
    public void T1_C_transitivity() throws Exception {
      runCommands(OPEN_FILE, "c.txt", CHECK_TRANSITIVITY);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("The graph is transitive");
    }

    @Test
    public void T1_A_antisymmetry() throws Exception {
      runCommands(OPEN_FILE, "a.txt", CHECK_ANTISYMMETRY);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("The graph is antisymmetric");
    }

    @Test
    public void T1_B_antisymmetry() throws Exception {
      runCommands(OPEN_FILE, "b.txt", CHECK_ANTISYMMETRY);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("The graph is NOT antisymmetric");
    }

    @Test
    public void T1_C_antisymmetry() throws Exception {
      runCommands(OPEN_FILE, "c.txt", CHECK_ANTISYMMETRY);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("The graph is NOT antisymmetric");
    }

    @Test
    public void T1_A_equivalence() throws Exception {
      runCommands(OPEN_FILE, "a.txt", CHECK_EQUIVALENCE);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("The graph is NOT an equivalence relation");
    }

    @Test
    public void T1_B_equivalence() throws Exception {
      runCommands(OPEN_FILE, "b.txt", CHECK_EQUIVALENCE);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("The graph is NOT an equivalence relation");
    }

    @Test
    public void T1_C_equivalence() throws Exception {
      runCommands(OPEN_FILE, "c.txt", CHECK_EQUIVALENCE);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("The graph is an equivalence relation");
    }

    @Test
    public void T1_B_equivalence_class_1() throws Exception {
      runCommands(OPEN_FILE, "b.txt", COMPUTE_EQUIVALENCE, 1);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("[]");
    }

    @Test
    public void T1_C_equivalence_class_0() throws Exception {
      runCommands(OPEN_FILE, "c.txt", COMPUTE_EQUIVALENCE, 0);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("[0]");
    }

    @Test
    public void T1_C_equivalence_class_1() throws Exception {
      runCommands(OPEN_FILE, "c.txt", COMPUTE_EQUIVALENCE, 1);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("[1, 2, 3]");
    }
  }

  public static class Task2 extends CliTest {
    public Task2() {
      super(Main.class);
    }

    @Test
    public void T2_A_iterative_BFS() throws Exception {
      runCommands(OPEN_FILE, "a.txt", GRAPH_SEARCH_IBFS);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("[0, 1, 2, 3, 4, 5]");
    }

    @Test
    public void T2_B_iterative_BFS() throws Exception {
      runCommands(OPEN_FILE, "b.txt", GRAPH_SEARCH_IBFS);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("[6, 5, 4, 3, 2, 1, 0]");
    }

    @Test
    public void T2_C_iterative_BFS() throws Exception {
      runCommands(OPEN_FILE, "c.txt", GRAPH_SEARCH_IBFS);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("[0, 1, 2, 3]");
    }

    @Test
    public void T2_A_iterative_DFS() throws Exception {
      runCommands(OPEN_FILE, "a.txt", GRAPH_SEARCH_IDFS);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("[0, 1, 3, 4, 5, 2]");
    }

    @Test
    public void T2_B_iterative_DFS() throws Exception {
      runCommands(OPEN_FILE, "b.txt", GRAPH_SEARCH_IDFS);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("[6, 5, 4, 3, 2, 1, 0]");
    }

    @Test
    public void T2_C_iterative_DFS() throws Exception {
      runCommands(OPEN_FILE, "c.txt", GRAPH_SEARCH_IDFS);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("[0, 1, 2, 3]");
    }
  }

  public static class Task3 extends CliTest {
    public Task3() {
      super(Main.class);
    }

    @Test
    public void T3_A_recursive_BFS() throws Exception {
      runCommands(OPEN_FILE, "a.txt", GRAPH_SEARCH_RBFS);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("[0, 1, 2, 3, 4, 5]");
    }

    @Test
    public void T3_B_recursive_BFS() throws Exception {
      runCommands(OPEN_FILE, "b.txt", GRAPH_SEARCH_RBFS);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("[6, 5, 4, 3, 2, 1, 0]");
    }

    @Test
    public void T3_C_recursive_BFS() throws Exception {
      runCommands(OPEN_FILE, "c.txt", GRAPH_SEARCH_RBFS);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("[0, 1, 2, 3]");
    }

    @Test
    public void T3_A_recursive_DFS() throws Exception {
      runCommands(OPEN_FILE, "a.txt", GRAPH_SEARCH_RDFS);
      assertContains("Successfully opened graph from file a.txt");
      assertContains("[0, 1, 3, 4, 5, 2]");
    }

    @Test
    public void T3_B_recursive_DFS() throws Exception {
      runCommands(OPEN_FILE, "b.txt", GRAPH_SEARCH_RDFS);
      assertContains("Successfully opened graph from file b.txt");
      assertContains("[6, 5, 4, 3, 2, 1, 0]");
    }

    @Test
    public void T3_C_recursive_DFS() throws Exception {
      runCommands(OPEN_FILE, "c.txt", GRAPH_SEARCH_RDFS);
      assertContains("Successfully opened graph from file c.txt");
      assertContains("[0, 1, 2, 3]");
    }
  }

  public static class YourTests extends CliTest {
    public YourTests() {
      super(Main.class);
    }

    @Test
    public void T2_D_recursive_BFS() throws Exception {
      runCommands(OPEN_FILE, "d.txt", GRAPH_SEARCH_RBFS);
      assertContains("Successfully opened graph from file d.txt");
      assertContains("[0, 1, 2, 3, 4, 5, 6, 10, 23, 32]");
    }

    @Test
    public void T2_F_recursive_BFS() throws Exception {
      runCommands(OPEN_FILE, "f.txt", GRAPH_SEARCH_RBFS);
      assertContains("Successfully opened graph from file f.txt");
      assertContains("[1, 3, 4, 6, 7, 5, 2, 8, 9, 30, 23]");
    }

    @Test
    public void T2_D_recursive_DFS() throws Exception {
      runCommands(OPEN_FILE, "d.txt", GRAPH_SEARCH_RDFS);
      assertContains("Successfully opened graph from file d.txt");
      assertContains("[0, 1, 2, 3, 4, 5, 6, 10, 23, 32]");
    }

    @Test
    public void T2_F_recursive_DFS() throws Exception {
      runCommands(OPEN_FILE, "f.txt", GRAPH_SEARCH_RDFS);
      assertContains("Successfully opened graph from file f.txt");
      assertContains("[1, 3, 6, 7, 4, 5, 2, 8, 9, 30, 23]");
    }

    @Test
    public void T2_D_iterative_BFS() throws Exception {
      runCommands(OPEN_FILE, "d.txt", GRAPH_SEARCH_IBFS);
      assertContains("Successfully opened graph from file d.txt");
      assertContains("[0, 1, 2, 3, 4, 5, 6, 10, 23, 32]");
    }

    @Test
    public void T2_F_iterative_BFS() throws Exception {
      runCommands(OPEN_FILE, "f.txt", GRAPH_SEARCH_IBFS);
      assertContains("Successfully opened graph from file f.txt");
      assertContains("[1, 3, 4, 6, 7, 5, 2, 8, 9, 30, 23]");
    }

    @Test
    public void T2_D_iterative_DFS() throws Exception {
      runCommands(OPEN_FILE, "d.txt", GRAPH_SEARCH_IDFS);
      assertContains("Successfully opened graph from file d.txt");
      assertContains("[0, 1, 2, 3, 4, 5, 6, 10, 23, 32]");
    }

    @Test
    public void T2_F_iterative_DFS() throws Exception {
      runCommands(OPEN_FILE, "f.txt", GRAPH_SEARCH_IDFS);
      assertContains("Successfully opened graph from file f.txt");
      assertContains("[1, 3, 6, 7, 4, 5, 2, 8, 9, 30, 23]");
    }

    @Test
    public void T1_D_roots() throws Exception {
      runCommands(OPEN_FILE, "d.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file d.txt");
      assertContains("[0, 1, 4, 10]");
    }

    @Test
    public void T1_E_roots() throws Exception {
      runCommands(OPEN_FILE, "e.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file e.txt");
      assertContains("[3]");
    }

    @Test
    public void T1_F_roots() throws Exception {
      runCommands(OPEN_FILE, "f.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file f.txt");
      assertContains("[1, 2, 30]");
    }

    @Test
    public void T1_G_roots() throws Exception {
      runCommands(OPEN_FILE, "g.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file g.txt");
      assertContains("[]");
    }

    @Test
    public void T1_G_reflexivity() throws Exception {
      runCommands(OPEN_FILE, "g.txt", CHECK_REFLEXIVITY);
      assertContains("Successfully opened graph from file g.txt");
      assertContains("The graph is NOT reflexive");
    }

    @Test
    public void T1_G_antisymmetry() throws Exception {
      runCommands(OPEN_FILE, "g.txt", CHECK_ANTISYMMETRY);
      assertContains("Successfully opened graph from file g.txt");
      assertContains("The graph is NOT antisymmetric");
    }

    @Test
    public void T1_G_transitivity() throws Exception {
      runCommands(OPEN_FILE, "g.txt", CHECK_TRANSITIVITY);
      assertContains("Successfully opened graph from file g.txt");
      assertContains("The graph is NOT transitive");
    }

    @Test
    public void T1_G_symmetry() throws Exception {
      runCommands(OPEN_FILE, "g.txt", CHECK_SYMMETRY);
      assertContains("Successfully opened graph from file g.txt");
      assertContains("The graph is symmetric");
    }

    @Test
    public void T1_G_equivalence() throws Exception {
      runCommands(OPEN_FILE, "g.txt", CHECK_EQUIVALENCE);
      assertContains("Successfully opened graph from file g.txt");
      assertContains("The graph is NOT an equivalence relation");
    }

    @Test
    public void T1_I_equivalence() throws Exception {
      runCommands(OPEN_FILE, "i.txt", CHECK_EQUIVALENCE);
      assertContains("Successfully opened graph from file i.txt");
      assertContains("The graph is NOT an equivalence relation");
    }

    @Test
    public void T1_I_roots() throws Exception {
      runCommands(OPEN_FILE, "i.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file i.txt");
      assertContains("[1, 4]");
    }

    @Test
    public void T1_H_roots() throws Exception {
      runCommands(OPEN_FILE, "h.txt", LIST_ROOT_VERTICIES);
      assertContains("Successfully opened graph from file h.txt");
      assertContains("[0, 2, 9, 19]");
    }

    @Test
    public void T2_H_iterative_BFS() throws Exception {
      runCommands(OPEN_FILE, "h.txt", GRAPH_SEARCH_IBFS);
      assertContains("Successfully opened graph from file h.txt");
      assertContains("[0, 22, 23, 8, 36, 3, 4, 5, 21, 2, 6, 7, 9, 19, 11]");
    }

    @Test
    public void T2_H_iterative_DFS() throws Exception {
      runCommands(OPEN_FILE, "h.txt", GRAPH_SEARCH_IDFS);
      assertContains("Successfully opened graph from file h.txt");
      assertContains("[0, 22, 8, 3, 36, 23, 4, 21, 5, 2, 6, 7, 9, 19, 11]");
    }

    @Test
    public void T2_H_recursive_DFS() throws Exception {
      runCommands(OPEN_FILE, "h.txt", GRAPH_SEARCH_RDFS);
      assertContains("Successfully opened graph from file h.txt");
      assertContains("[0, 22, 8, 3, 36, 23, 4, 21, 5, 2, 6, 7, 9, 19, 11]");
    }

    @Test
    public void T2_H_recursive_BFS() throws Exception {
      runCommands(OPEN_FILE, "h.txt", GRAPH_SEARCH_RBFS);
      assertContains("Successfully opened graph from file h.txt");
      assertContains("[0, 22, 23, 8, 36, 3, 4, 5, 21, 2, 6, 7, 9, 19, 11]");
    }
  }
}
