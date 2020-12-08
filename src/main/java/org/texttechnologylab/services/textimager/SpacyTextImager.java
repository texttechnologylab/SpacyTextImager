package org.texttechnologylab.services.textimager;

import org.apache.uima.UIMAException;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.hucompute.textimager.client.TextImagerClient;
import org.junit.Test;

import java.io.IOException;

/**
 * Method to preprocess a JCas via TextImager
 * @author Giuseppe Abrami
 */
public class SpacyTextImager {

    @Test
    public void test(){

        try {
            JCas tCas = JCasFactory.createText("Dies ist ein toller Test!", "de");
            JCasUtil.select(runProcess(tCas), Annotation.class).forEach(a->{
                System.out.println(a);
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UIMAException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private static TextImagerClient TIClient = null;

    public static JCas runProcess(JCas pCas) throws Exception {

        if(TIClient==null){
            TIClient = new TextImagerClient();
            TIClient.setConfigFile("src/main/resources/services.xml");
        }
        TIClient.process(pCas.getCas(), new String[]{"SpaCyMultiTagger"});
        return pCas;
    }


}
