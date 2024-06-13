import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class FileManagementSystem {
    private Directory root;
    private Map<String, FileSystemNode> deletedItems;
    private Stack<FileSystemNode> deletedItemsStack;

    public FileManagementSystem() {
        root = new Directory("/");
        deletedItems = new HashMap<>();
        deletedItemsStack = new Stack<>();
    }

    // Module 1: Create a new directory and file in all levels
    public void createDirectory(String path, String name) {
        Directory parent = navigateToDirectory(path);
        if (parent != null) {
            parent.addChild(new Directory(name));
            System.out.println("Directory created successfully.");
        } else {
            System.out.println("Invalid path.");
        }
    }

    public void createFile(String path, String name, String content) {
        Directory parent = navigateToDirectory(path);
        if (parent != null) {
            parent.addChild(new File(name, content));
            System.out.println("File created successfully.");
        } else {
            System.out.println("Invalid path.");
        }
    }

    // Module 2: List all directories and files
    public void listAll(String path) {
        Directory dir = navigateToDirectory(path);
        if (dir != null) {
            listRecursive(dir, 0);
        } else {
            System.out.println("Invalid path.");
        }
    }

    private void listRecursive(Directory dir, int level) {
        for (FileSystemNode node : dir.getChildren()) {
            for (int i = 0; i < level; i++) {
                System.out.print("  ");
            }
            System.out.println(node.getName());
            if (node instanceof Directory) {
                listRecursive((Directory) node, level + 1);
            }
        }
    }

    // Module 3: Update file content
    public void updateFileContent(String path, String name, String newContent) {
        File file = (File) findNode(path, name);
        if (file != null) {
            file.setContent(newContent);
            System.out.println("File content updated successfully.");
        } else {
            System.out.println("File not found.");
        }
    }

    // Module 4: Update directory and file names
    public void updateNodeName(String path, String oldName, String newName) {
        FileSystemNode node = findNode(path, oldName);
        if (node != null) {
            node.setName(newName);
            System.out.println("Name updated successfully.");
        } else {
            System.out.println("Node not found.");
        }
    }

    // Module 5: Delete directory and file
    public void deleteNode(String path, String name) {
        Directory parent = navigateToDirectory(path);
        if (parent != null) {
            FileSystemNode node = findNode(path, name);
            if (node != null) {
                parent.removeChild(node);
                deletedItems.put(name, node);
                deletedItemsStack.push(node);
                System.out.println("Node deleted successfully.");
            } else {
                System.out.println("Node not found.");
            }
        } else {
            System.out.println("Invalid path.");
        }
    }

    // Module 6: Restore deleted directories and files
    public void restoreLastDeleted() {
        if (!deletedItemsStack.isEmpty()) {
            FileSystemNode node = deletedItemsStack.pop();
            deletedItems.remove(node.getName());
            root.addChild(node);
            System.out.println("Last deleted node restored successfully.");
        } else {
            System.out.println("No nodes to restore.");
        }
    }

    // Helper Methods
    private Directory navigateToDirectory(String path) {
        String[] parts = path.split("/");
        Directory current = root;
        for (String part : parts) {
            if (part.isEmpty()) continue;
            boolean found = false;
            for (FileSystemNode node : current.getChildren()) {
                if (node instanceof Directory && node.getName().equals(part)) {
                    current = (Directory) node;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return null;
            }
        }
        return current;
    }

    private FileSystemNode findNode(String path, String name) {
        Directory parent = navigateToDirectory(path);
        if (parent != null) {
            for (FileSystemNode node : parent.getChildren()) {
                if (node.getName().equals(name)) {
                    return node;
                }
            }
        }
        return null;
    }
}
