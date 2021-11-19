package org.dreambot.framework;


import org.dreambot.api.script.AbstractScript;

import java.util.List;
import java.util.stream.Collectors;

public class Tree<T extends AbstractScript> {
    private final Root<T> root;

     /**
     *Instantiate a new Root upon new Tree instantiation
     */
    public Tree() {
        root = new Root<>();
    }

    public Leaf<T> addBranch(Leaf<T>... leaves) {
        root.addLeafs(leaves);
        return root;
    }

    public void clear() {
        root.children.clear();
    }

    public List<String> getActiveBranches() {
        return root.children.stream().map(tLeaf -> tLeaf.getClass().getSimpleName()).collect(Collectors.toList());
    }

    public int onLoop() {
        return root.onLoop();
    }
}
