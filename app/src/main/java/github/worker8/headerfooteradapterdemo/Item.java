package github.worker8.headerfooteradapterdemo;

public class Item {

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void toggleSelection() {
        selected = !selected;
    }
}
