/***
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Matthew William Carter
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.sky.mattca.bml;

import com.sky.mattca.bml.Lexer.Lexer;
import com.sky.mattca.bml.Lexer.TokenString;
import com.sky.mattca.bml.Lexer.TokenType;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BMLFile {

    private String fileLocation;
    private BMLObject rootObject;

    private Lexer lexer;

    public BMLFile(String fileLocation) {
        this.fileLocation = fileLocation;
        lexer = new Lexer();
    }

    public String getFileLocation() {
        return fileLocation;
    }

    private void loadFromFileLocation() {
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileLocation))) {
            BufferedReader input = new BufferedReader(reader);
            String line = "";
            while (input.ready()) {
                line = input.readLine();
                lexer.addSourceLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<TokenString> tokenized = lexer.start();
        for (TokenString str: tokenized) {
            str.removeWhitespace();
            if (str.match(TokenType.IDENTIFIER, TokenType.ASSIGNMENT)) {

            } else if (str.match(TokenType.IDENTIFIER, TokenType.STRUCTURE_DECLARATION)) {

            } else if (str.match(TokenType.IDENTIFIER, TokenType.FILE_REFERENCE)) {

            } else {
                Handler.reportError(new Handler.BuildError(6, str.line, 0));
            }
        }
    }

    private boolean isProperty(TokenString string) {
        if (string.skip(TokenType.TAB).match(TokenType.IDENTIFIER, TokenType.ASSIGNMENT)) {
            return true;
        } else {
            return false;
        }
    }

    private BMLProperty parseProperty(TokenString string) {
        TokenString trimmed = string.skip(TokenType.TAB).removeWhitespace();
        String name = trimmed.consume().contents;
        trimmed.consume();

        if (trimmed.match(TokenType.STRING_LITERAL)) {

        } else if (trimmed.match(TokenType.INTEGER_LITERAL)) {

        } else if (trimmed.match(TokenType.FLOAT_LITERAL)) {

        } else if (trimmed.match(TokenType.FLOAT_LITERAL, TokenType.VALUE_SEPARATOR, TokenType.FLOAT_LITERAL)) {

        } else if (trimmed.match(TokenType.FLOAT_LITERAL, TokenType.VALUE_SEPARATOR, TokenType.FLOAT_LITERAL, TokenType.VALUE_SEPARATOR, TokenType.FLOAT_LITERAL)) {

        } else {

        }
    }

    private BMLObject handleObject(String name, int myLayer, List<TokenString> tokenStrings) {
        BMLObject newObject = new BMLObject(name, myLayer);
        for (TokenString str: tokenStrings) {
            int stringLayer = myLayer;
            while (str.match(TokenType.TAB)) {
                stringLayer ++;
            }

            if (stringLayer == myLayer) {
                // This string is a part of this object
                if (isProperty(str)) {
                    newObject.add(parseProperty(str));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "";
    }

}
