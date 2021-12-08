(ns adv.day8
  (:require [clojure.set :as set]
            [clojure.string :as str]
            [adv.util :as util]))

(def input ["gfbcda" "cgfadb" "badcefg" "eacfgd" "gfd" "edagcbf" "cdfebg" "gfedab" "fcg" "fcbae" "fg" "aecgd" "fabdc" "cg" "bdfeg" "gbc" "cdegaf" "dbfgea" "adefbg" "afedcbg" "egfdbc" "ecgfbd" "dbcagef" "cafbed" "dg" "cgabe" "decfb" "dg" "gbafec" "bfgea" "fgecab" "cefdba" "acgfeb" "dgbca" "cegbfa" "cadbf" "dcb" "acged" "edbcfa" "dbgecaf" "agb" "ba" "gdbaf" "fcbgea" "fcbag" "cegad" "gdace" "cdf" "gbfed" "fecagb" "fdbcga" "afcbd" "acbg" "abcg" "gcba" "bc" "cg" "cg" "ecfadg" "ecgdabf" "cfd" "edcb" "bfdage" "edcb" "af" "gabecd" "dgfcaeb" "afb" "adgef" "gd" "cgfbdae" "dgfacb" "agfcdbe" "fcb" "cbf" "bc" "cdgbefa" "bfacge" "ebcfga" "gfcab" "gacbd" "dgeaf" "cfdb" "fab" "ecadgfb" "dabgef" "beagdf" "ecgbd" "acgf" "edcga" "cabgdfe" "gacf" "acdgfb" "fbdgec" "agbec" "dbcefg" "egdcb" "bgcdef" "da" "da" "bec" "gaced" "afedb" "eadgc" "bcdaef" "bcefad" "efcadb" "gae" "fde" "abedf" "def" "aedfb" "ebcfd" "bfda" "dcb" "egcafbd" "baefgc" "defcab" "eb" "cadef" "fcde" "agcde" "efcd" "cgd" "fc" "dcabf" "cebf" "afdbc" "fgb" "fg" "fbdaec" "daefb" "dfc" "gcfedba" "dfecg" "agfd" "dfcae" "fecabgd" "eac" "aedfgcb" "fcdbeg" "ge" "bfaecd" "ebfcd" "egfba" "cfgae" "dcfe" "ec" "efbad" "egdc" "afcbdg" "ce" "ceagbd" "gdabc" "aced" "adec" "gdacfeb" "abedg" "fbgdec" "cbedagf" "acdfeg" "ebagf" "ce" "efcgba" "acfbde" "dbacge" "cadgb" "gfab" "fagdec" "becd" "dfb" "fgbca" "dgcfae" "dcb" "daeb" "bd" "bfdagc" "acdgb" "bacdg" "dfga" "bcedgfa" "fgbcda" "bcgadf" "edgfab" "gfcbade" "dafegbc" "adfgbce" "fbegc" "dcegf" "eb" "badgcfe" "gabe" "dceagb" "dgbf" "bf" "fcb" "acd" "bacegf" "gcefda" "gdeca" "ca" "abce" "gfdea" "gbcfed" "gf" "fgbaed" "fgabcde" "cdfge" "gbecadf" "gfabec" "afd" "afgcde" "adc" "cbfgdea" "dca" "abcgfd" "ebdaf" "abdg" "gde" "cgeaf" "deg" "aebdf" "gcafd" "egac" "fedcgb" "ecdga" "facd" "cd" "cgf" "gfc" "decagf" "cdfae" "dgfcba" "cad" "fcagd" "baecfd" "dfce" "eagdbf" "faebc" "gacefb" "fbaeg" "eabcgdf" "eagfb" "afdbe" "fadcg" "fagecbd" "baf" "cfeagd" "ed" "dcagefb" "dgeabfc" "bdfeag" "bfc" "gaebdf" "cfb" "afedcb" "febdg" "bdfegc" "gfcd" "defgb" "agfb" "gaebcdf" "bgedcaf" "caf" "bdgfcea" "dbgc" "cbgfea" "bcaefd" "bfcage" "dbfga" "gadbf" "bagdf" "fdbca" "acdbfeg" "ec" "ce" "gafcbde" "gca" "cga" "dgcbfa" "gedcba" "cgbe" "cdgeab" "bc" "ac" "cagf" "fbaedg" "afedc" "cgfbaed" "dbacgef" "aebgcf" "gfa" "bdefcag" "bgfcd" "ef" "cdebf" "feadgbc" "gbedc" "bgeadc" "abdgef" "cgbe" "eb" "cebg" "geabfd" "efcbg" "gca" "eacfd" "dgcebf" "fbdgac" "cfdga" "cadegfb" "cgfbd" "fdgeab" "efbd" "fb" "abgce" "ecbdfag" "gbdac" "gcbedf" "cgfebd" "dagfe" "ec" "adfeg" "ce" "afec" "bfa" "fgbed" "fab" "faedbg" "gfdba" "gfadb" "ebagdfc" "gcd" "abfcg" "ebdcfga" "dbgca" "egcabfd" "egcfb" "cdgfba" "fbd" "gad" "dagcb" "dcfageb" "cgafb" "dabgfec" "fbgacd" "badfgc" "abcdegf" "fb" "fb" "fbg" "gcebd" "fae" "ceab" "fcageb" "ebfag" "gfbea" "acf" "egbfdc" "agcd" "ebgfd" "dgbae" "gbfde" "gab" "eafb" "eadgbc" "fgb" "dgfac" "fceabg" "dcge" "cg" "gc" "gcabed" "gbcda" "gedb" "bdcga" "cefgb" "dcbfag" "acgdef" "adgcfe" "afg" "fga" "gacfd" "fgaced" "cda" "aebgdc" "fagd" "caebgd" "cdfgae" "dbgca" "deacfb" "gf" "efdba" "eafgb" "dabfgec" "cgfadb" "afbced" "bdfagce" "dacgb" "bfeg" "bfcag" "cgeb" "agc" "cgfabe" "ebfcgad" "aedfg" "cafdeg" "dcefga" "dfbcae" "fdgcb" "efacbgd" "bdacegf" "fgbca" "cbfagd" "acgbfde" "fca" "agcfe" "bf" "gbcef" "gdbec" "begfc" "acbgf" "gfcba" "eb" "abcde" "cg" "gc" "gbc" "fbcgea" "fbc" "fbecda" "fdca" "fad" "dgca" "gdefb" "afbcg" "gbdec" "eba" "edfbca" "agfdcbe" "eabfdg" "fdceg" "cedga" "cgdae" "facbd" "bgdc" "db" "cbaef" "adgcb" "fegacdb" "cafdeb" "geabd" "cdfbg" "dg" "edcg" "afdbc" "gedcba" "bf" "fgbd" "bdfeagc" "dbcaeg" "bdaegfc" "dbfcage" "bafd" "fa" "eadfbc" "cbega" "cfage" "adgef" "gebf" "gdfbace" "gfeb" "gef" "adfg" "fg" "fge" "cadf" "dbagce" "ad" "dfac" "gdefbac" "abfed" "ac" "dfcaeb" "egdcaf" "cgebdf" "gca" "agc" "bgce" "cgf" "gfc" "gcadbf" "cfaed" "cefad" "fadgeb" "dgfca" "gabcd" "bf" "acgdb" "bf" "egdb" "ebfag" "dga" "ebfacg" "dcgfa" "cb" "dgabfc" "cgb" "agefc" "baecf" "ecbd" "bcf" "ecfd" "dfgcb" "edfcabg" "dcabgf" "eabdg" "dgfa" "afegdb" "agebfcd" "gda" "ad" "afcgb" "efgbd" "cba" "geabd" "cbgda" "bac" "gdaeb" "gbade" "gca" "gdace" "edabf" "gfcbad" "dafcgeb" "bad" "begd" "gcbfdea" "bg" "daegc" "fc" "bceafd" "gabcd" "dfabcge" "cefgdab" "cdaebg" "gedfcab" "facegd" "eacg" "adc" "efdgab" "dac" "fdcg" "fbecag" "egdafc" "efagbc" "bdegf" "gcaefd" "dagbf" "fdeacb" "gadbcfe" "ebfgc" "decfbg" "gfecdb" "cgd" "dg" "gdc" "gadbcf" "cdeg" "gdec" "ebfdgc" "ed" "fdc" "dfc" "bcad" "dc" "facgb" "gacdb" "egcabfd" "dcbgef" "dcbeg" "aceb" "gbdce" "fedga" "be" "gfacedb" "cgdaefb" "ecbagd" "gecdabf" "dbecga" "efadcg" "dafecg" "cfbe" "cbfag" "bdgfa" "cgeafb" "adgce" "dacfg" "gcaefb" "ce" "cb" "bdfcea" "fcdagb" "dgcb" "aegd" "bedacf" "gade" "egad" "acdeg" "fdbaec" "ged" "gebc" "bgcfae" "ad" "bdgefca" "dgfae" "cegfa" "cgedb" "fd" "gaefc" "dcagfb" "caebd" "fcbade" "bdc" "afdbcge" "eacfdbg" "cae" "dega" "dbafc" "eafbg" "gdbace" "bgc" "bd" "bcgafd" "cbgafd" "gbdcfe" "gecb" "bc" "gbce" "bc" "cegfdab" "gdfeab" "fbd" "gfebdca" "bfcdg" "agdfecb" "gcabfde" "acgefd" "afecbg" "gabcfe" "fc" "cf" "ab" "bacdg" "ab" "gdbcfea" "dfecb" "bfcga" "da" "dcfeb" "ge" "adfgcb" "gdaecfb" "eabdgf" "bfgced" "ecgbd" "egcdfa" "fd" "acfb" "ecgabfd" "fa" "fea" "dag" "gbadfce" "gd" "bgdeafc" "fd" "ebfd" "fd" "defb" "efabcg" "abdc" "gcfdb" "afdge" "efdca" "cdaef" "ecbgfad" "fdcbag" "ebgfd" "cagdfb" "gfbdeac" "ag" "cgeaf" "fcdega" "cadfeg" "dfgabce" "bgdeaf" "deg" "aedc" "edca" "af" "acf" "bdfceg" "edbcfg" "cfebgad" "egcdba" "ebcfa" "bdgeac" "cd" "aegcbfd" "fbadg" "fdacebg" "abedf" "caegfbd" "afgcb" "beafcdg" "dg" "dbg" "gd" "gd" "dgcefa" "abg" "adfb" "gba" "dgafc" "agfcd" "fb" "fb" "ac" "gceda" "gbca" "agbc" "dace" "dacgef" "gec" "gfcedba" "ge" "acegbfd" "egab" "ageb" "fcd" "ebfad" "gfcbda" "fcd" "dafbe" "dgbcfe" "efga" "af" "faedb" "bag" "edfab" "fcdgb" "deb" "fcdgeba" "gbeacd" "cedagf" "gdf" "bgdec" "bcegd" "gbedf" "dcaegfb" "cbf" "egdbca" "dgcfa" "badecg" "egfdba" "dbegafc" "gfbe" "ebcd" "fceadb" "cbeagf" "bdacef" "cgedab" "gdf" "fgd" "gdf" "begcdaf" "ca" "ceag" "fgceb" "gbdcf" "cdag" "bcdfg" "fdbegc" "cfgba" "adfbgce" "cf" "cbagf" "gdfae" "cgbdef" "gadef" "gfcb" "egabcdf" "agcedb" "cfegab" "egbdc" "ega" "dagebf" "gdfbae" "gbdeac"])

;; 2 -> 2 segments in common with 4
;; 3 -> 3 segments in common with 4, 3 common with 7
;; 5 -> 3 segments in common with 4, 2 common with 7

;; 0 -> 3 common with 7, 3 common with 4
;; 6 -> 2 common with 7
;; 9 -> 3 common with 7, 4 common with 4

(defn- in-common [a b]
  (count
   (set/intersection a b)))

(defn- disambiguate [patterns]
  (let [one (first (filter #(= (count %) 2) patterns))
        seven (first (filter #(= (count %) 3) patterns))
        four (first (filter #(= (count %) 4) patterns))
        eight (first (filter #(= (count %) 7) patterns))]
    (into {}
          (map (fn [pattern]
                 (vector pattern
                         (cond
                           (and (= (count pattern) 6) (= 3 (in-common pattern seven)) (= 3 (in-common pattern four))) 0
                           (= pattern one) 1
                           (and (= (count pattern) 5) (= 2 (in-common pattern four))) 2
                           (and (= (count pattern) 5) (= 3 (in-common pattern four)) (= 3 (in-common pattern seven))) 3
                           (= pattern four) 4
                           (and (= (count pattern) 5) (= 3 (in-common pattern four)) (= 2 (in-common pattern seven))) 5
                           (and (= (count pattern) 6) (= 2 (in-common pattern seven))) 6
                           (= pattern seven) 7
                           (= pattern eight) 8
                           (and (= (count pattern) 6) (= 3 (in-common pattern seven)) (= 4 (in-common pattern four))) 9))) patterns))))

(defn part1 []
  (count
   (filter #(contains? #{2 4 3 7} (count %)) input)))

(defn- process-line [s]
  (let [[_ patterns output] (re-matches #"([^\|]+)\|([^\|]+)" s)
        patterns (-> patterns str/trim (str/split #" "))
        patterns (map #(set (str/split % #"")) patterns)
        output (-> output str/trim (str/split #" "))
        output (map #(set (str/split % #"")) output)
        ref (disambiguate patterns)]
    (->>
     (map #(get ref %) output)
     str/join
     (Integer/parseInt))))

(defn part2 []
  (let [lines (util/lines "day8.txt")]
    (reduce + (map process-line lines))))
