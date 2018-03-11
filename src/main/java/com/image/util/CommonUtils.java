package com.image.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import opennlp.tools.util.InputStreamFactory;

public class CommonUtils {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	public static void main(String[] args) throws FileNotFoundException, IOException {
		List<File> files = CommonUtils.getResourceFolderFiles("training-data/person");

		for (File file : files) {
			try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			}
		}

	}

	/**
	 * Returns all files in a folder
	 * 
	 * @param folder
	 * @return File[]
	 */
	public static List<File> getResourceFolderFiles(String folder) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		URL url = loader.getResource(folder);
		String path = url.getPath();

		return Arrays.asList(new File(path).listFiles());
	}

	public static InputStreamFactory trainingDataInputStreamFactory(List<File> trainingFiles) {
		return new InputStreamFactory() {
			@Override
			public InputStream createInputStream() throws IOException {
				List<InputStream> inputStreams = trainingFiles.stream().map(f -> {
					try {
						return new FileInputStream(f);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
						return null;
					}
				}).filter(Objects::nonNull).collect(Collectors.toList());

				return new SequenceInputStream(new Vector<>(inputStreams).elements());
			}
		};
	}

	public static void printFile(String fileName) {
		byte[] fileArray = null;
		try {
			fileArray = Files.readAllBytes(Paths.get(fileName));
		} catch (IOException e) {
			logger.error("Error ", e);
		}

		try {
			String wikiString = new String(fileArray, "ISO-8859-1");
			logger.info("wikiString: {}", wikiString);
		} catch (UnsupportedEncodingException e) {
			logger.error("Error ", e);
		}
	}

	/**
	 * Returns country code to Country name map.
	 * 
	 * @return
	 */
	public static Map<String, String> getCountryCodeToNameMap() {
		Map<String, String> map = new HashMap<>();
		map.put("ac", "Ascension Island");
		map.put("ad", "Andorra");
		map.put("ae", "United Arab Emirates");
		map.put("af", "Afghanistan");
		map.put("ag", "Antigua and Barbuda");
		map.put("ai", "Anguilla");
		map.put("al", "Albania");
		map.put("am", "Armenia");
		map.put("an", "Netherlands Antilles");
		map.put("ao", "Angola");
		map.put("aq", "Antarctica");
		map.put("ar", "Argentina");
		map.put("as", "American Samoa");
		map.put("at", "Austria");
		map.put("au", "Australia");
		map.put("aw", "Aruba");
		map.put("ax", "Aland Islands");
		map.put("az", "Azerbaijan");
		map.put("ba", "Bosnia and Herzegovina");
		map.put("bb", "Barbados");
		map.put("bd", "Bangladesh");
		map.put("be", "Belgium");
		map.put("bf", "Burkina Faso");
		map.put("bg", "Bulgaria");
		map.put("bh", "Bahrain");
		map.put("bi", "Burundi");
		map.put("bj", "Benin");
		map.put("bl", "Saint Barthelemy");
		map.put("bm", "Bermuda");
		map.put("bn", "Brunei Darussalam");
		map.put("bo", "Bolivia");
		map.put("br", "Brazil");
		map.put("bs", "Bahamas");
		map.put("bt", "Bhutan");
		map.put("bv", "Bouvet Island");
		map.put("bw", "Botswana");
		map.put("by", "Belarus");
		map.put("bz", "Belize");
		map.put("ca", "Canada");
		map.put("cc", "Cocos (Keeling) Islands");
		map.put("cd", "Congo, Democratic Republic");
		map.put("cf", "Central African Republic");
		map.put("cg", "Congo");
		map.put("ch", "Switzerland");
		map.put("ci", "Cote D'Ivoire (Ivory Coast)");
		map.put("ck", "Cook Islands");
		map.put("cl", "Chile");
		map.put("cm", "Cameroon");
		map.put("cn", "China");
		map.put("co", "Colombia");
		map.put("cr", "Costa Rica");
		map.put("cu", "Cuba");
		map.put("cv", "Cape Verde");
		map.put("cx", "Christmas Island");
		map.put("cy", "Cyprus");
		map.put("cz", "Czech Republic");
		map.put("de", "Germany");
		map.put("dj", "Djibouti");
		map.put("dk", "Denmark");
		map.put("dm", "Dominica");
		map.put("do", "Dominican Republic");
		map.put("dz", "Algeria");
		map.put("ec", "Ecuador");
		map.put("ee", "Estonia");
		map.put("eg", "Egypt");
		map.put("eh", "Western Sahara");
		map.put("er", "Eritrea");
		map.put("es", "Spain");
		map.put("et", "Ethiopia");
		map.put("eu", "European Union");
		map.put("fi", "Finland");
		map.put("fj", "Fiji");
		map.put("fk", "Falkland Islands (Malvinas)");
		map.put("fm", "Micronesia");
		map.put("fo", "Faroe Islands");
		map.put("fr", "France");
		map.put("ga", "Gabon");
		map.put("gb", "Great Britain (UK)");
		map.put("gd", "Grenada");
		map.put("ge", "Georgia");
		map.put("gf", "French Guiana");
		map.put("gg", "Guernsey");
		map.put("gh", "Ghana");
		map.put("gi", "Gibraltar");
		map.put("gl", "Greenland");
		map.put("gm", "Gambia");
		map.put("gn", "Guinea");
		map.put("gp", "Guadeloupe");
		map.put("gq", "Equatorial Guinea");
		map.put("gr", "Greece");
		map.put("gs", "S. Georgia and S. Sandwich Isls.");
		map.put("gt", "Guatemala");
		map.put("gu", "Guam");
		map.put("gw", "Guinea-Bissau");
		map.put("gy", "Guyana");
		map.put("hk", "Hong Kong");
		map.put("hm", "Heard and McDonald Islands");
		map.put("hn", "Honduras");
		map.put("hr", "Croatia");
		map.put("ht", "Haiti");
		map.put("hu", "Hungary");
		map.put("id", "Indonesia");
		map.put("ie", "Ireland");
		map.put("il", "Israel");
		map.put("im", "Isle of Man");
		map.put("in", "India");
		map.put("io", "British Indian Ocean Territory");
		map.put("iq", "Iraq");
		map.put("ir", "Iran");
		map.put("is", "Iceland");
		map.put("it", "Italy");
		map.put("je", "Jersey");
		map.put("jm", "Jamaica");
		map.put("jo", "Jordan");
		map.put("jp", "Japan");
		map.put("ke", "Kenya");
		map.put("kg", "Kyrgyzstan");
		map.put("kh", "Cambodia");
		map.put("ki", "Kiribati");
		map.put("km", "Comoros");
		map.put("kn", "Saint Kitts and Nevis");
		map.put("kp", "Korea, Democratic Republic of");
		map.put("kr", "Korea, Republic of");
		map.put("kw", "Kuwait");
		map.put("ky", "Cayman Islands");
		map.put("kz", "Kazakhstan");
		map.put("la", "Laos");
		map.put("lb", "Lebanon");
		map.put("lc", "Saint Lucia");
		map.put("li", "Liechtenstein");
		map.put("lk", "Sri Lanka");
		map.put("lr", "Liberia");
		map.put("ls", "Lesotho");
		map.put("lt", "Lithuania");
		map.put("lu", "Luxembourg");
		map.put("lv", "Latvia");
		map.put("ly", "Libya");
		map.put("ma", "Morocco");
		map.put("mc", "Monaco");
		map.put("md", "Moldova");
		map.put("me", "Montenegro");
		map.put("mf", "Saint Martin (French part)");
		map.put("mg", "Madagascar");
		map.put("mh", "Marshall Islands");
		map.put("mk", "Macedonia");
		map.put("ml", "Mali");
		map.put("mm", "Myanmar");
		map.put("mn", "Mongolia");
		map.put("mo", "Macau");
		map.put("mp", "Northern Mariana Islands");
		map.put("mq", "Martinique");
		map.put("mr", "Mauritania");
		map.put("ms", "Montserrat");
		map.put("mt", "Malta");
		map.put("mu", "Mauritius");
		map.put("mv", "Maldives");
		map.put("mw", "Malawi");
		map.put("mx", "Mexico");
		map.put("my", "Malaysia");
		map.put("mz", "Mozambique");
		map.put("na", "Namibia");
		map.put("nc", "New Caledonia");
		map.put("ne", "Niger");
		map.put("nf", "Norfolk Island");
		map.put("ng", "Nigeria");
		map.put("ni", "Nicaragua");
		map.put("nl", "Netherlands");
		map.put("no", "Norway");
		map.put("np", "Nepal");
		map.put("nr", "Nauru");
		map.put("nu", "Niue");
		map.put("nz", "New Zealand (Aotearoa)");
		map.put("om", "Oman");
		map.put("pa", "Panama");
		map.put("pe", "Peru");
		map.put("pf", "French Polynesia");
		map.put("pg", "Papua New Guinea");
		map.put("ph", "Philippines");
		map.put("pk", "Pakistan");
		map.put("pl", "Poland");
		map.put("pm", "St. Pierre and Miquelon");
		map.put("pn", "Pitcairn");
		map.put("pr", "Puerto Rico");
		map.put("ps", "Palestinian Territory, Occupied");
		map.put("pt", "Portugal");
		map.put("pw", "Palau");
		map.put("py", "Paraguay");
		map.put("qa", "Qatar");
		map.put("re", "Reunion");
		map.put("ro", "Romania");
		map.put("rs", "Serbia");
		map.put("ru", "Russian Federation");
		map.put("rw", "Rwanda");
		map.put("sa", "Saudi Arabia");
		map.put("sb", "Solomon Islands");
		map.put("sc", "Seychelles");
		map.put("sd", "Sudan");
		map.put("se", "Sweden");
		map.put("sg", "Singapore");
		map.put("sh", "St. Helena");
		map.put("si", "Slovenia");
		map.put("sj", "Svalbard and Jan Mayen Islands");
		map.put("sk", "Slovakia");
		map.put("sl", "Sierra Leone");
		map.put("sm", "San Marino");
		map.put("sn", "Senegal");
		map.put("so", "Somalia");
		map.put("sr", "Suriname");
		map.put("st", "Sao Tome and Principe");
		map.put("su", "Soviet Union");
		map.put("sv", "El Salvador");
		map.put("sy", "Syrian Arab Republic");
		map.put("sz", "Swaziland");
		map.put("tc", "Turks and Caicos Islands");
		map.put("td", "Chad");
		map.put("tf", "French Southern Territories");
		map.put("tg", "Togo");
		map.put("th", "Thailand");
		map.put("tj", "Tajikistan");
		map.put("tk", "Tokelau");
		map.put("tl", "Timor-Leste");
		map.put("tm", "Turkmenistan");
		map.put("tn", "Tunisia");
		map.put("to", "Tonga");
		map.put("tp", "Portuguese Timor");
		map.put("tr", "Turkey");
		map.put("tt", "Trinidad and Tobago");
		map.put("tv", "Tuvalu");
		map.put("tw", "Taiwan");
		map.put("tz", "Tanzania");
		map.put("ua", "Ukraine");
		map.put("ug", "Uganda");
		map.put("uk", "United Kingdom");
		map.put("um", "US Minor Outlying Islands");
		map.put("us", "United States");
		map.put("uy", "Uruguay");
		map.put("uz", "Uzbekistan");
		map.put("va", "Vatican City State (Holy See)");
		map.put("vc", "Saint Vincent and the Grenadines");
		map.put("ve", "Venezuela");
		map.put("vg", "Virgin Islands (British)");
		map.put("vi", "Virgin Islands (U.S.)");
		map.put("vn", "Viet Nam");
		map.put("vu", "Vanuatu");
		map.put("wf", "Wallis and Futuna Islands");
		map.put("ws", "Samoa");
		map.put("ye", "Yemen");
		map.put("yt", "Mayotte");
		map.put("yu", "Yugoslavia");
		map.put("za", "South Africa");
		map.put("zm", "Zambia");
		map.put("zw", "Zimbabwe");

		return map;
	}
}
