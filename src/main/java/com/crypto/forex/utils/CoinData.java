package com.crypto.forex.utils;

import java.util.HashMap;
import java.util.Map;

public class CoinData {
  public static final String[] coins =
      {"BTC", "ETH", "XRP", "BCH", "LTC", "ADA", "NEO", "XLM", "EOS", "IOTA", "YOYO", "WAN",
      "MIOTA", "XMR", "DASH", "XEM", "TRX", "USDT", "ETC", "VEN", "QTUM", "LSK", "OMG", "NANO",
      "BTG", "ICX", "ZEC", "BNB", "DGD", "PPT", "STEEM", "STRAT", "BCN", "XVG", "WAVES", "SC",
      "MKR", "BTS", "RHOC", "DOGE", "SNT", "REP", "AE", "DCR", "WTC", "BTM", "AION", "ONT", "ZIL",
      "KMD", "ARDR", "ARK", "ZRX", "KCS", "CNX", "HSR", "DGB", "GAS", "PIVX", "MONA", "SYS", "DRGN",
      "ETN", "VERI", "ETHOS", "BAT", "GNT", "FCT", "R", "NAS", "LRC", "QASH", "XZC", "FUN", "KNC",
      "GXS", "ELF", "RDD", "BTCD", "IOST", "MAID", "SALT", "EMC", "NXT", "NCASH", "REQ", "POWR",
      "GBYTE", "LINK", "KIN", "DCN", "PART", "POLY", "PAY", "BNT", "NEBL", "CND", "NXS", "ENG",
      "DENT", "ICN", "MNX", "NULS", "BLOCK", "SMART", "STORJ", "VTC", "SUB", "SRN", "GNO", "GVT",
      "PLR", "QSP", "AGI", "MTL", "MANA", "ANT", "BTX", "SAN", "ACT", "ENJ", "THETA", "CVC", "DTR",
      "MCO", "GAME", "CS", "RLC", "WAX", "RDN", "STORM", "IGNIS", "NAV", "GNX", "BOS", "TNB", "BIX",
      "PXS", "UBQ", "PPP", "SKY", "POE", "ZEN", "XPA", "XAS", "ABT", "POA", "SLS", "FTC", "AUTO",
      "EVN", "DEW", "XP", "HPB", "PURA", "EDG", "XDN", "PRL", "FSN", "ADX", "MED", "BCO", "BLZ",
      "VIBE", "EDO", "LEND", "C20", "VEE", "ION", "SPHTX", "ITC", "EMC2", "TEL", "MDS", "SNM",
      "QRL", "DATA", "CMT", "AST", "XBY", "NGC", "RCN", "PPC", "OST", "WGR", "AMB", "BAY", "SPC",
      "JNT", "RPX", "WINGS", "TAAS", "ERA", "SPANK", "TRAC", "MLN", "APPC", "SMT", "DBC", "NLG",
      "GTO", "VIA", "UTNP", "ETP", "LBC", "BCPT", "BURST", "SNGLS", "MOD", "UTK", "WABI", "BRD",
      "LOOM", "TNC", "SOAR", "CRPT", "INS", "WPR", "DTA", "XCP", "HTML", "GRS", "MGO", "INK",
      "CLOAK", "BTO", "TRIG", "DPY", "AEON", "TNT", "FUEL", "PRE", "MOBI", "HVN", "UNO", "AMP",
      "DNT", "HAV", "GTC", "HMQ", "HST", "CRW", "TKN", "CPC", "KICK", "BITCNY", "CDT", "RKT", "LUN",
      "LKK", "CTR", "NMC", "IDH", "PEPECASH", "ONION", "POT", "LEO", "BITB", "UCASH", "CFI", "ECA",
      "SBD", "NLC2", "RFR", "SXDT", "FLASH", "VIB", "ATM", "SHIFT", "QLC", "XWC", "INT", "SAFEX",
      "DCT", "MTH", "BPT", "DIME", "ADT", "UKG", "ZCL", "SIB", "IHT", "ZPT", "ECC", "MER", "NET",
      "ZAP", "REN", "ORME", "IOC", "EVX", "BKX", "NMR", "RVR", "COLX", "MTN", "DMD", "FOTA", "LET",
      "SNC", "UQC", "MOON", "YOYOW", "CHSB", "XEL", "DAT", "PPY", "VRC", "TRST", "BLK", "EDR",
      "BCC", "ICOS", "RISE", "MSP", "ARN", "GRC", "PASC", "ZSC", "OCN", "DAI", "MDA", "XPM", "GRID",
      "ELEC", "COSS", "EXP", "QUN", "OMNI", "MUE", "DLT", "TIO", "LYM", "BLT", "SWFTC", "ALQO",
      "TSL", "KEY", "CV", "XSH", "RVN", "BMC", "DADI", "CAPP", "QBT", "RADS", "HMC", "TIX", "1ST",
      "SOC", "GUP", "COV", "PRG", "SLR", "PZM", "IXT", "STK", "OAX", "TAU", "LA", "SNOV", "DTB",
      "XRL", "BITUSD", "BDG", "DBET", "DRT", "DNA", "STQ", "PRO", "WCT", "ALIS", "NVST", "BOT",
      "ZOI", "TRUE", "MWAT", "MINT", "FLO", "IPL", "POSW", "THC", "RMC", "DIVX", "TGT", "B2B",
      "XUC", "NYC", "CHP", "RVT", "RBY", "AURA", "DEB", "XAUR", "LMC", "INCNT", "LINDA", "BSD",
      "CLAM", "ATN", "SWT", "AIT", "CAN", "NEU", "COFI", "EZT", "GBX", "STX", "OCT", "MUSIC", "HKN",
      "QAU", "TX", "ENRG", "EVR", "PHR", "IFT", "AUR", "CSNO", "UNIT", "MOT", "OXY", "XMY", "EAC",
      "CAT", "OK", "PRA", "DOPE", "GAM", "LOC", "TOA", "XSPEC", "BBR", "KRM", "TIME", "BIS",
      "USNBT", "NEOS", "DIM", "PTOY", "TCC", "ATB", "DYN", "AIR", "FLDC", "HAC", "HBT", "LUX",
      "PLBT", "NXC", "SPF", "EKO", "AXP", "KB3", "DBIX", "TCT", "GOLOS", "FDX", "MEE", "XLR",
      "SYNX", "MDT", "TIE", "MYB", "PND", "MYST", "DICE", "POLIS", "OTN", "COVAL", "PUT", "XNK",
      "BQ", "HOT", "RNT", "BUN", "PLU", "POLL", "PST", "SPHR", "BNTY", "TIPS", "NVC", "REM", "BLUE",
      "CXO", "PIRL", "PFR", "MTX", "CAG", "ESP", "PINK", "HORSE", "TKS", "AC", "XST", "EBTC", "SEQ",
      "AVT", "BCY", "WRC", "ELIX", "HEAT", "CVCOIN", "CURE", "FAIR", "VOISE", "GET", "RMT", "REBL",
      "IOP", "GCN", "SXUT", "DNR", "SETH", "AID", "HGT", "TUSD", "DRP", "XVC", "BEZ", "1337",
      "OBITS", "CHIPS", "GMT", "INSTAR", "PKT", "CAT", "GEO", "BET", "ATMS", "ADB", "NTRN", "FLIXX",
      "BIO", "LIFE", "BTM", "ASTRO", "HYP", "APX", "TFL", "RC", "DOT", "ABY", "QWARK", "HWC",
      "PARETO", "PTC", "FRD", "HUSH", "ERO", "BRX", "BTCZ", "ARY", "KORE", "BWK", "GAT", "TDX",
      "UFO", "BPL", "IPBC", "SNRG", "XBC", "EXCL", "MNTP", "ZRC", "MEME", "EVE", "VTR", "GLD",
      "LEDU", "NIO", "TBX", "LEV", "SUMO", "PLAY", "PBL", "PBT", "CPAY", "ZLA", "MCAP", "TRC",
      "DOVU", "NOTE", "VRM", "MONK", "GRFT", "TRF", "BTDX", "SLT", "HAT", "INXT", "VIU", "J8T",
      "ERC", "VSL", "XMCC", "KZC", "FLIK", "REX", "YOC", "PRIX", "2GIVE", "UFR", "RIC", "BASH",
      "ADC", "OPT", "BELA", "ZEIT", "VZT", "CMPCO", "WISH", "TRCT", "DTH", "MTNC", "BON", "CANN",
      "SSS", "SHP", "VSX", "ING", "BRK", "AIX", "IFLT", "ALT", "EXRN", "PIPL", "HUC", "ODN",
      "CRAVE", "KRB", "TZC", "LOCI", "CPY", "BUZZ", "QRK", "SPR", "GRE", "SPRTS", "CREA", "PUT",
      "ZER", "TES", "PIX", "CRED", "SCL", "ADST", "ATL", "SAGA", "EFYT", "INN", "JC", "EGC", "REAL",
      "EBST", "CRB", "BLU", "GJC", "BLITZ", "TRUST", "REC", "PING", "SXC", "GCR", "EMV", "HXX",
      "EFL", "DGPT", "XGOX", "SWIFT", "NKC", "SEND", "STAK", "CHC", "XMG", "ELLA", "WILD", "LINX",
      "ZNY", "NKA", "QVT", "DFT", "FOR", "MXT", "LDOGE", "IND", "ITNS", "AMM", "LCT", "XFT", "WTT",
      "PKB", "MAG", "CL", "RUP", "PROC", "BTW", "SMS", "CBX", "UNB", "PURE", "OCL", "NSR", "RAIN",
      "RUPX", "ADZ", "ONG", "SKIN", "MZC", "GCC", "ELTCOIN", "HOLD", "PLC", "PYLNT", "NOBL", "BXT",
      "STU", "DAR", "MRT", "ARC", "PHO", "BBP", "DP", "GRWI", "DAY", "MRJA", "ETBS", "VIVO", "IC",
      "CRC", "SCT", "UNIFY", "ACC", "PDC", "DEUS", "MOIN", "JEW", "DRPU", "WDC", "MBRS", "INSN",
      "MAGE", "UIS", "KEK", "XPTX", "LOG", "ZENI", "KLN", "ARG", "FST", "FUCK", "ZET", "BYC", "CRM",
      "SKC", "CCRB", "RNS", "XPD", "RLT", "BDL", "B@", "ESZ", "NET", "BRO", "EQL", "FJC", "LGD",
      "XLC", "PIGGY", "Q2C", "START", "DEM", "ARCT", "GUN", "POP", "FRST", "HERO", "HBN", "JET",
      "DCY", "ECASH", "HPC", "ITT", "SUR", "TTC", "OPC", "BTCS", "GRIM", "CTX", "BTCA", "DFS",
      "MANNA", "BTA", "QBIC", "CNT", "DGC", "MEC", "RKC", "CPC", "ELE", "MAC", "WHL", "ITI", "BLZ",
      "SMC", "PCN", "EL", "CV2", "KBR", "MOJO", "WGO", "ORE", "RBT", "XCN", "ATS", "GLS", "PXC",
      "AERM", "EBCH", "CJ", "ARI", "MNE", "NETKO", "ENT", "ETG", "DRXNE", "TIG", "BTWTY", "ONX",
      "CCO", "BITSILVER", "POST", "GB", "XJO", "8BIT", "CFT", "ACE", "XCPO", "EPY", "BITBTC",
      "ERC20", "LCP", "PNX", "TKR", "QBC", "ZCG", "GRLC", "PAK", "TEK", "TAG", "BTG", "TOKC", "XHI",
      "PR", "HVCO", "STN", "BTCRED", "OTX", "AIB", "BLOCKPAY", "ACC", "SGR", "BOLI", "GOLF", "ARCO",
      "KAYI", "NEWB", "ZZC", "XVP", "BITGOLD", "VOT", "DAXX", "ETHD", "XCXT", "CCT", "PCOIN", "808",
      "MCRN", "GTC", "NTO", "BRIA", "HBC", "DSR", "SPACE", "CUBE", "EOT", "GLT", "ECO", "SHND",
      "BSTY", "FUNC", "REE", "SANDG", "NUKO", "INFX", "PASL", "ICOB", "CAT", "MAY", "LBTC", "QTL",
      "611", "BITEUR", "DALC", "DUO", "VUC", "GPU", "COAL", "CACH", "GCC", "SOON", "BTPL", "$$$",
      "BIP", "MSCN", "CXT", "ALL", "PRX", "BNX", "GP", "ERY", "ZYD", "QCN", "KRONE", "BSTAR",
      "ALTCOM", "WOMEN", "GBC", "MILO", "PLACO", "BOAT", "VRS", "PRC", "XRC", "COUPE", "ULA",
      "NANOX", "VLTC", "HMC", "DMB", "AI", "BRIT", "COB", "XTO", "SWM", "MUSE", "KLC", "ART",
      "CREDO", "HDG", "ECOB", "ECN", "EVC", "VTA", "XNN", "STAR", "UNY", "STA", "AHT", "ZEPH",
      "SENSE", "IXC", "ETT", "PGL", "YASH", "CARBON", "BBT", "GOOD", "FYP", "FYN", "LEAF", "STRC",
      "EQT", "TRIA", "SMLY", "USC", "UNI", "SHORTY", "ORB", "V", "42", "INPAY", "AU", "FLT", "ANC",
      "ICOO", "METAL", "MAX", "SDC", "CDN", "BPC", "I0C", "NDC", "EBET", "CDX", "HTC", "FCN",
      "KOBO", "UNIC", "MBI", "BITS", "TROLL", "TRI", "RUSTBITS", "WAND", "FIMK", "GAIA", "LANA",
      "TRUMP", "TIT", "VISIO", "HODL", "LNK", "TALK", "RIYA", "FC2", "BTCR", "BTB", "VAL", "OPAL",
      "IETH", "HAL", "VIDZ", "UTC", "AMBER", "NYAN", "WAY", "ABJ", "SIGT", "XBL", "KURT", "FLY",
      "SCORE", "MAO", "XGR", "BITZ", "SUPER", "BUCKS", "CNO", "TRK", "KUSH", "XPY", "SLG", "MOTO",
      "DDF", "BLC", "DSH", "GAP", "ICN", "MNC", "MARS", "CHESS", "BIGUP", "TGC", "VC", "SRC", "BCF",
      "DIX", "CCN", "XRA", "TSE", "DTC", "BERN", "FRC", "SAC", "MNM", "NEVA", "PX", "CYP", "ATOM",
      "STV", "PHS", "EVIL", "UNITS", "RBIES", "XCT", "CRX", "SPEX", "RED", "C2", "GLC", "EMD",
      "XRE", "SHDW", "AMMO", "XIOS", "BUMBA", "LTB", "DLC", "CFD", "SWING", "ZUR", "HONEY", "YTN",
      "IRL", "IMS", "PLC", "LEA", "300", "SDRN", "XNG", "BOST", "888", "AMS", "CHAN", "KED", "MAD",
      "HNC", "PXI", "JIN", "RPC", "GUESS", "J", "SCRT", "ASAFE2", "MST", "FIRE", "EVO", "ISL",
      "VLT", "TRDT", "IMX", "POS", "YAC", "ICE", "EAGLE", "XCRE", "ADCN", "EUC", "XBTC21", "ROOFS",
      "CON", "SOJ", "FUZZ", "BRAT", "SCS", "HMP", "ELC", "SOIL", "FRK", "TAJ", "FLAX", "DBTC",
      "FNC", "ACOIN", "ZMC", "ANTI", "WARP", "MAR", "CPN", "CESC", "LUNA", "MDC", "NTWK", "BLN",
      "SPT", "CF", "CMT", "XCO", "DRS", "EXN", "NRO", "BENJI", "MTLMC3", "BLRY", "SH", "CAB", "BAS",
      "SLEVIN", "BTQ", "GPL", "ATX", "CTO", "VPRC", "CNNC", "LTCR", "VIP", "URO", "MND", "SFC",
      "PULSE", "DLISK", "RMC", "BXC", "WORM", "SONG", "PONZI", "DRM", "ARB", "RIDE", "WBB", "CASH",
      "ICON", "STARS", "BSC", "LTCU", "URC", "VEC2", "JWL", "UET", "PIE", "G3N", "ORLY", "KNC",
      "STEPS", "RSGP", "IMPS", "XBTS", "PEX", "LIR", "BIOS", "DES", "JOBS", "CWXT", "ACP", "GEERT",
      "BRAIN", "RBX", "ZNE", "TAGR", "CRT", "EGO", "PLNC", "OS76", "JS", "OFF", "CRDNC", "DOLLAR",
      "VOLT", "TOR", "ALTC", "SDP", "XOC", "COXST", "IBANK", "ARGUS", "ELS", "BIOB", "CTIC3",
      "CREVA", "SOCC", "P7C", "NODC", "FXE", "CONX", "SLFI", "MGM", "CTIC2", "GSR", "LVPS", "CALC",
      "TYCHO", "CCM100", "DGCS", "TSTR", "PIZZA", "ABN", "EBT", "DIBC", "HT", "ATMC", "EKT", "ELA",
      "OC", "RUFF", "TOPC", "UP", "EPC", "AIDOC", "WIC", "BCD", "CHAT", "LDC", "DRG", "W3C", "LBTC",
      "TEN", "YEE", "FRGC", "RCT", "AAC", "XTZ", "MLM", "HLC", "SHOW", "AVH", "OCC", "BTCP", "OF",
      "TKY", "SSC", "SEXC", "UGT", "VLC", "GLA", "XIN", "MAN", "FAIR", "STC", "SBTC", "DXT",
      "KCASH", "TOMO", "MOF", "UGC", "IQT", "REF", "UBTC", "BCX", "CMS", "NTK", "LIGHT", "GEM",
      "BSR", "FIL", "READ", "DDD", "BANCA", "PRS", "CLUB", "$PAC", "CFUN", "CMS", "LCC", "NANJ",
      "WETH", "WC", "MSD", "ACAT", "SWTC", "MOAC", "EAG", "DMT", "QUBE", "DROP", "BAX", "JIYO",
      "CAN", "AWR", "GETX", "CENNZ", "B2X", "SHIP", "TER", "ADK", "MGC", "TFD", "SBC", "HIRE",
      "XID", "FLUZ", "BCDN", "CANDY", "XIN", "BIG", "IPC", "TMC", "BEE", "DATX", "LALA", "ZENGOLD",
      "CEFS", "NMS", "SIC", "UIP", "THS", "CAS", "EXY", "IDT", "BAR", "COR", "IFC", "MVC", "ECH",
      "BCA", "ENT", "ACC", "UTT", "HQX", "MAG", "ANI", "UNITY", "TOK", "XID", "GCS", "GBG", "WA",
      "IDXM", "BT2", "HC", "TOKEN", "STAC", "VASH", "LEVO", "MCR", "HPY", "GMX", "ABC", "BTE",
      "DAV", "TCOIN", "CLD", "ACES", "STEX", "PRES", "OX", "GOD", "SND", "BITCF", "WIC", "SPK",
      "TESLA", "SJCX", "XOT", "GARY", "ZBC", "ESC", "BAT", "FRN", "DMC", "VULC", "CSC", "BIT",
      "TOP", "FLAP", "PCS", "BTCM", "SUP", "AV", "BLAZR", "ACN", "BET", "SIGMA", "XRY", "FONZ",
      "CMP", "WOW", "QBT", "LEPEN", "INDI", "GRX", "PDG", "PLX", "GRN", "SAK", "SLOTH", "MARX",
      "CFC", "EDRC", "SKR", "ELITE", "SJW", "NAMO", "ROYAL", "BSN", "WSX", "CRYPT", "OP", "GDC",
      "RICHX", "FAZZ", "LKC", "HIGH", "NUMUS", "CYDER", "HDLB", "TOPAZ", "FID", "WINK", "UR",
      "RUBIT", "RBBT", "XSTC", "NEOG", "HYPER", "ETT", "DON", "XQN", "SFE", "SHA", "AKY", "CHEAP",
      "BEST", "ASN", "INDIA", "SPORT", "BTBc", "MAGN", "APC", "RYZ", "DISK", "DUTCH", "XVE", "BAC",
      "SHELL", "TODAY", "PRN", "HNC", "FAP", "BUB", "HALLO", "BIRDS", "SISA", "ZSE", "NBIT", "XTD",
      "NOX", "DUB", "REGA", "MCI", "CME", "LDCN", "UNRC", "MINEX", "LAZ", "CYC", "ANTX", "TELL",
      "SMOKE", "NTC", "TRICK", "KDC", "SKULL", "GAY", "OPES", "MBL", "UNC", "DASHS", "GAIN",
      "PRIMU", "TEAM", "MMXVI", "AXIOM", "CC", "FUTC", "TURBO", "MONETA", "FRWC", "X2", "SNAKE",
      "YES", "HCC", "VOYA", "LTH", "PAYP", "EGOLD", "RUNNERS", "QORA", "KARMA", "GML", "XAU", "EGG",
      "DCRE", "IVZ", "PSY", "TCR", "PRM", "DBG", "POKE", "OMC", "RCN", "KASHH", "RHFC", "TLE",
      "BITOK", "LLT", "SAY", "OCOW", "INF", "FRCT", "XMRG"};

  public static final String[] currencies = {"USD", "INR"};

  public static final Map<String, String> coinMap = fetchMapFromCoins();

  public static final Map<String, String> coinOverrideMap = fetchOverrideCoinsMap();

  public static Map<String, String> fetchMapFromCoins() {
    final Map<String, String> coinMap = new HashMap<>();
    for (int i = 0; i < coins.length; i++) {
      coinMap.put(coins[i], coins[i]);
    }
    for (int i = 0; i < currencies.length; i++) {
      coinMap.put(currencies[i], currencies[i]);
    }

    return coinMap;
  }

  public static final Map<String,String> fetchOverrideCoinsMap(){
    final Map<String,String> overrideMap = new HashMap<>();
    overrideMap.put("BCC", "BCH");
    return overrideMap;
  }

  public static final String getOverrideCoinValue(final String coinSym) {
    if (coinSym == null) {
      return coinSym;
    }
    return coinOverrideMap.getOrDefault(coinSym.toUpperCase(), coinSym);
  }
}
