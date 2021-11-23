package com.pb.abaieva.hw9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileNumbers {

    public static void createNumbersFile() throws IOException {

        Path numFile = Paths.get("files/numbers.txt");

        BufferedWriter writeNumFile = Files.newBufferedWriter(numFile);

        for (int i = 0; i < 10; i++) {

            for (int k = 0; k < 10; k++) {
                int randNum = (int) (Math.random()*100);
                writeNumFile.write(String.valueOf(randNum));
                writeNumFile.write(" ");
            }

            writeNumFile.newLine();

        }

        writeNumFile.close();
    }

    public static void createOddNumbersFile() throws IOException {
        Path numFile = Paths.get("files/numbers.txt");

        Scanner scanNum = new Scanner(numFile).useDelimiter(" ");

        int[] numArray = new int[200];
        int i = 0;

        while (scanNum.hasNext()) {

            for (int k = 0; k < 10; k++) {

                String scannedNum = scanNum.next();

                numArray[i] = Integer.parseInt(scannedNum);
                i++;

            }

            scanNum.nextLine();

        }

        BufferedWriter writeNumFile = Files.newBufferedWriter(numFile);

        i = 0;

        for (int m = 0; m < 10; m++) {

            for (int a = 0; a < 10; a++) {

                if ((numArray[i] % 2) != 0) {
                    writeNumFile.write(String.valueOf(numArray[i]));
                } else {
                    writeNumFile.write("0");
                }

                writeNumFile.write(" ");

                i++;

            }

            writeNumFile.newLine();

        }

        writeNumFile.close();

    }

    public static void main(String[] args) throws IOException {
        createNumbersFile();

        createOddNumbersFile();

    }

}

/*

  ,-.       _,---._ __  / \
 /  )    .-'       `./ /   \
(  (   ,'            `/    /|
 \  `-"             \'\   / |
  `.              ,  \ \ /  |
   /`.          ,'-`----Y   |
  (            ;        |   '
  |  ,-.    ,-'         |  /
  |  | (   |       logs | /
  )  |  \  `.___________|/
  `--'   `--'
*/