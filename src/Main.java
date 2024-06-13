import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileManagementSystem fms = new FileManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nFile Management System");
            System.out.println("1. Create a new directory");
            System.out.println("2. Create a new file");
            System.out.println("3. List all directories and files");
            System.out.println("4. Update file content");
            System.out.println("5. Update directory or file name");
            System.out.println("6. Delete directory or file");
            System.out.println("7. Restore deleted directory or file");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter the path where you want to create a directory: ");
                    String dirPath = scanner.nextLine();
                    System.out.print("Enter the directory name: ");
                    String dirName = scanner.nextLine();
                    fms.createDirectory(dirPath, dirName);
                    break;
                case 2:
                    System.out.print("Enter the path where you want to create a file: ");
                    String filePath = scanner.nextLine();
                    System.out.print("Enter the file name: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Enter the file content: ");
                    String fileContent = scanner.nextLine();
                    fms.createFile(filePath, fileName, fileContent);
                    break;
                case 3:
                    System.out.print("Enter the path to list directories and files: ");
                    String listPath = scanner.nextLine();
                    fms.listAll(listPath);
                    break;
                case 4:
                    System.out.print("Enter the path of the file: ");
                    String updateFilePath = scanner.nextLine();
                    System.out.print("Enter the file name: ");
                    String updateFileName = scanner.nextLine();
                    System.out.print("Enter the new content: ");
                    String newContent = scanner.nextLine();
                    fms.updateFileContent(updateFilePath, updateFileName, newContent);
                    break;
                case 5:
                    System.out.print("Enter the path of the directory or file: ");
                    String updateNodePath = scanner.nextLine();
                    System.out.print("Enter the current name: ");
                    String currentName = scanner.nextLine();
                    System.out.print("Enter the new name: ");
                    String newName = scanner.nextLine();
                    fms.updateNodeName(updateNodePath, currentName, newName);
                    break;
                case 6:
                    System.out.print("Enter the path of the directory or file: ");
                    String deleteNodePath = scanner.nextLine();
                    System.out.print("Enter the name: ");
                    String deleteName = scanner.nextLine();
                    fms.deleteNode(deleteNodePath, deleteName);
                    break;
                case 7:
                    fms.restoreLastDeleted();
                    break;
                case 8:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
