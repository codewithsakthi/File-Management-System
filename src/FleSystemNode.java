import java.util.ArrayList;
import java.util.List;

class FileSystemNode {
    protected String name;

    public FileSystemNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class File extends FileSystemNode {
    private String content;

    public File(String name, String content) {
        super(name);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class Directory extends FileSystemNode {
    private List<FileSystemNode> children;

    public Directory(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public List<FileSystemNode> getChildren() {
        return children;
    }

    public void addChild(FileSystemNode child) {
        children.add(child);
    }

    public void removeChild(FileSystemNode child) {
        children.remove(child);
    }
}
