/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opennlp.modelbuilder.v2.impls;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import opennlp.modelbuilder.v2.SentenceProvider;

/**
 *
 * @author Owner
 */
public class FileSentenceProvider implements SentenceProvider {

  private Map<String, String> params = new HashMap<String, String>();
  Set<String> sentences = new HashSet<String>();

  public Set<String> getSentences() {
     if (sentences.isEmpty()) {
      try {
        InputStream fis;
        BufferedReader br;
        String line;

        fis = new FileInputStream(params.get("sentencesfile"));
        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        while ((line = br.readLine()) != null) {
          sentences.add(line);
        }

        // Done with the file
        br.close();
        br = null;
        fis = null;
      } catch (FileNotFoundException ex) {
        Logger.getLogger(FileKnownEntityProvider.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(FileKnownEntityProvider.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return sentences;
  }

  public void setParameters(Map<String, String> params) {
    this.params = params;
  }
}