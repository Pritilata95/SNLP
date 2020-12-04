/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LevenshteinDistance;

/**
 *
 * @author ASUS
 */
public class mainLD {
    public static void main(String[] args){
        String text1 = "bqsvtzfhktnqovnjmkktluayihcfelhfqfsordvkxrmweenobehobnokrzabxcglfavxwyqlhjjlfxyx"
    + "ddptklfuwvtvwgecpdskvovhrrlaoqzjqkpauhpkkrxtrwmrevimykuyvstfgsmeegqjvxcstegiiqgl"
    + "auprgfuchymgvznavfpxnflxlujvtsshwhsnkunetfiwvqppaanugcovhnvwwedxywtkswmfeccatpna"
    + "fzntnagkjpsinosekeahzmqtcpjkaxzmunclbcurppwumfnmxlrfgjxsddblhiorwiaqcjlbnbfkdjfr"
    + "fqeqzrlfoiejfcmwdrcxneoquogbbuwdsykxvjpmfahmoybrqmneyrlutiunrbhfclzzhdtlxnwivoeg"
    + "zfzxmargbkxkjnqiuqsgakhzgxkxtchewatusacyeodaankidwezlopjabyzaxpxzffxnqaredklqron"
    + "slyzqgtfdjkaiwdcgalwvgpnlbxvuyqmhxsudcthfgziahugmjdhpqjucyvffviupnzmdtbatqbfewhy"
    + "jyfsijxxuyoyonldvclzrdoujbaskietubkfoopbeucqrgxzvmkwcdmbuglfcnkizkefskvmqazbtyyn"
    + "lrwptebsqvrtwtfukoesbjtgcxesvimzzdyoqvnclbedqhhgirhdyexourkwhzpjxjctdvqpiprfedsr"
    + "xywdzncqihboggtekrknjbrlflrfegxjbvaqqjcodbzmvgefbateguibcajueuohwftimarzgjjygyng"
    + "ayihcjnlnhidcooojummtbjuaddjsfyxqqpmphsuicxeeaizqrsrdomxhxwymjtfuigiijwowdoolbdv"
    + "bvwtipnybxndkubuzskeycpjuuiutokzlibbprywcnovjrbrhmlsoyumyxuaswbkoopaxbndrjyocqle"
    + "ishdwuuummzmtovjidjetgsfftrfmiqjckfphsvt";
        String text2 = "bqsvtzfhktnuosnjmkktluayihcfelhfqfsordvkxrmweenobelobnocrzabxcglnavxcyqlhjjlfxyx"
    + "odptklfurvtvwiecpdrkvovhrrlnoizjqkpauhpkkrxtrwmrmvimykuyvstfgsmeenqjvxcstegiiqgl"
    + "aufrgfuchymgvznavfpxnflxlujvtssxwhsnkuneqfiwvqpdaanugcnvhavwwedxywtkswmfeccatpna"
    + "fzntnagkjpsinosekeahzmqtcpnkapzmunclbcurppwumfnmxmrfglxjddblhiorwiaqcjlbnbfkdjfw"
    + "fqeqzrjfoiejkcmwdrcxneonuogbmtwdsfkxvjpkfahmrynrqgneyrfutiunrbhfclzzhdtlxpwinocg"
    + "zfzxmargbkzkjnqiuqsgakfzgxkctbwewatusacyeodaankidwyzlopqabyzaxpxzffxnqaredkjqron"
    + "slyzqgtfdokaiwdcgalwvtpnlbxvuyqmhxsudcthfgzidhugmjdhpqjucyvffviupnzbdnbatqbfewhy"
    + "jyflcjxxuyoyonldvcpardoujbaskietubkfoopbeucqrgxzvmkwcdmbuglfqnkizkefhkvmqazbtkvn"
    + "lrwptebsqvrtwtfukoesbjtgcxesvimzzdpoqvnclbedqhomcrhdyexouukwwzpfxjctdiqpiprfedsr"
    + "xyodzncqijbogutekrknbbrlflrfegxjbvaqqjcodbzmvgefbateguiscajueuohjftimvrzgjjygymg"
    + "zyihcjnlnzidcaoojulmtbjuaddjslyxqlpmxhsuibxeeaizqrkrdomxhxwymjtfuigiijwowdoolbdv"
    + "svwtihnybxndkubhzskeycpjuuiutokzlibbprywcnovjrbrvllsogumyxuwswqkoouaxbnidjywcqle"
    + "ishbwuuummzmtovjidjetgsfftrfmiqjckfphsvt";
        LevenshteinDistance LD = new LevenshteinDistance();
        System.out.println(LD.calculate("thames", "teh"));
    }
    
}
