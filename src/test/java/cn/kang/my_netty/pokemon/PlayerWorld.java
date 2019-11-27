package cn.kang.my_netty.pokemon;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PlayerWorld {
    private ConcurrentMap<Integer, Integer> curLevels = new ConcurrentHashMap<Integer, Integer>();
    private int dailyPvpTimes;
    private int dailyExploreTimes;
    private List<Explores> exploresList = new ArrayList<Explores>();
    private int lastUsedBallId;

    public static PlayerWorld deserialize(byte[] data)
            throws InvalidProtocolBufferException {
        PlayerWorld playerWorld = new PlayerWorld();
        com.leocool.sgland.protocol.Data.PlayerWorld worldData = com.leocool.sgland.protocol.Data.PlayerWorld.parseFrom((byte[]) data);
        worldData.getCurLevelsList().forEach(e -> {
            playerWorld.curLevels.put((e - 10000) / 100, e);
        });

        List<Explores> exploresList = new ArrayList<Explores>();
        worldData.getExploresList().forEach(e -> {
            Explores explores = new Explores();
            explores.setDilayPvpTimes(e.getDailyPvpTimes());
            explores.setDailyExploreTimes(e.getDailyExploreTimes());
            explores.setLevel(e.getLevel());
            explores.setMonster(e.getMonster());
            int[] meet = new int[e.getMeetRecordsList().size()];
            for (int i = 0; i < e.getMeetRecordsList().size(); i++) {
                meet[i] = e.getMeetRecordsList().get(i);
            }
            explores.setMeetRecords(meet);
            explores.setDisappearTime(e.getDisappearTime());
            explores.setCurCatchTimes(e.getCurCatchTimes());
            explores.setMaxCatchtTimes(e.getMaxCatchTimes());
            ArrayList<Explores.CatchRecord> catchRecords = new ArrayList<>();
            List<com.leocool.sgland.protocol.Data.CatchRecords> catchRecordsList = e.getCatchRecordsList();
            for (com.leocool.sgland.protocol.Data.CatchRecords c : e.getCatchRecordsList()) {
                catchRecords.add(new Explores.CatchRecord(c.getInfoId(), c.getNum()));
            }
            explores.setCatchRecords(catchRecords);
            exploresList.add(explores);
        });
        playerWorld.setExploresList(exploresList);
        playerWorld.setDailyPvpTimes(worldData.getDailyPvpTimes());
        playerWorld.setDailyExploreTimes(worldData.getDailyExploreTimes());
        playerWorld.setLastUsedBallId(worldData.getLastUsedBallId());
        return playerWorld;
    }

    public com.leocool.sgland.protocol.Data.PlayerWorld pack() {
        com.leocool.sgland.protocol.Data.PlayerWorld.Builder rb = com.leocool.sgland.protocol.Data.PlayerWorld.newBuilder();
/*		IntStream.rangeClosed((int) 1, (int) 3).forEach(e -> {
			rb.addCurLevels(((Integer) this.curLevels.getOrDefault(e, 0)).intValue());
		});*/
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(this.curLevels.entrySet());
        list.stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .filter(e -> e.getValue() > 0)
                .forEach(e -> rb.addCurLevels(e.getValue()));

        for (Explores explores : this.exploresList) {
            com.leocool.sgland.protocol.Data.Explores.Builder builder = com.leocool.sgland.protocol.Data.Explores.newBuilder();
            builder.setDailyPvpTimes(explores.dilayPvpTimes);
            builder.setDailyExploreTimes(explores.dailyExploreTimes);
            builder.setLevel(explores.level);
            builder.setMonster(explores.monster);
            //builder.addAllMeetRecords(Ints.asList(explores.meetRecords));
            builder.setDisappearTime(explores.disappearTime);
            builder.setCurCatchTimes(explores.curCatchTimes);
            builder.setMaxCatchTimes(explores.maxCatchtTimes);
            explores.catchRecords.forEach(e -> builder.addCatchRecords(com.leocool.sgland.protocol.Data.CatchRecords.newBuilder().setInfoId(e.InfoId).setNum(e.num)).build());
            com.leocool.sgland.protocol.Data.Explores build = builder.build();
            rb.addExplores(build);
        }
        rb.setDailyPvpTimes(this.dailyPvpTimes);
        rb.setDailyExploreTimes(this.dailyExploreTimes);
        rb.setLastUsedBallId(this.lastUsedBallId);
        return rb.build();
    }

    public ConcurrentMap<Integer, Integer> getCurLevels() {
        return curLevels;
    }

    public void setCurLevels(ConcurrentMap<Integer, Integer> curLevels) {
        this.curLevels = curLevels;
    }

    public int getDailyPvpTimes() {
        return dailyPvpTimes;
    }

    public void setDailyPvpTimes(int dailyPvpTimes) {
        this.dailyPvpTimes = dailyPvpTimes;
    }

    public int getDailyExploreTimes() {
        return dailyExploreTimes;
    }

    public void setDailyExploreTimes(int dailyExploreTimes) {
        this.dailyExploreTimes = dailyExploreTimes;
    }

    public List<Explores> getExploresList() {
        return exploresList;
    }

    public void setExploresList(List<Explores> exploresList) {
        this.exploresList = exploresList;
    }

    public int getLastUsedBallId() {
        return lastUsedBallId;
    }

    public void setLastUsedBallId(int lastUsedBallId) {
        this.lastUsedBallId = lastUsedBallId;
    }

    public static class Explores {
        private int dilayPvpTimes;
        private int dailyExploreTimes;
        private int level;
        private int monster;
        private int[] meetRecords;
        private long disappearTime;
        private int curCatchTimes;
        private int maxCatchtTimes;
        private List<CatchRecord> catchRecords = new ArrayList<>();

        public int getDilayPvpTimes() {
            return dilayPvpTimes;
        }

        public void setDilayPvpTimes(int dilayPvpTimes) {
            this.dilayPvpTimes = dilayPvpTimes;
        }

        public int getDailyExploreTimes() {
            return dailyExploreTimes;
        }

        public void setDailyExploreTimes(int dailyExploreTimes) {
            this.dailyExploreTimes = dailyExploreTimes;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getMonster() {
            return monster;
        }

        public void setMonster(int monster) {
            this.monster = monster;
        }

        public int[] getMeetRecords() {
            return meetRecords;
        }

        public void setMeetRecords(int[] meetRecords) {
            this.meetRecords = meetRecords;
        }

        public long getDisappearTime() {
            return disappearTime;
        }

        public void setDisappearTime(long disappearTime) {
            this.disappearTime = disappearTime;
        }

        public int getCurCatchTimes() {
            return curCatchTimes;
        }

        public void setCurCatchTimes(int curCatchTimes) {
            this.curCatchTimes = curCatchTimes;
        }

        public int getMaxCatchtTimes() {
            return maxCatchtTimes;
        }

        public void setMaxCatchtTimes(int maxCatchtTimes) {
            this.maxCatchtTimes = maxCatchtTimes;
        }

        public List<CatchRecord> getCatchRecords() {
            return catchRecords;
        }

        public void setCatchRecords(List<CatchRecord> catchRecords) {
            this.catchRecords = catchRecords;
        }

        public Explores() {
            this.maxCatchtTimes = 10;
            this.catchRecords = new ArrayList<>();
        }

        public com.leocool.sgland.protocol.Data.Explores pack() {
            com.leocool.sgland.protocol.Data.Explores.Builder rb = com.leocool.sgland.protocol.Data.Explores.newBuilder();
            rb.setDailyPvpTimes(this.getDilayPvpTimes());
            rb.setDailyExploreTimes(this.dailyExploreTimes);
            rb.setLevel(this.getLevel());
            rb.setMonster(this.getMonster());
            rb.setDisappearTime(this.getDisappearTime());
            rb.setCurCatchTimes(this.getCurCatchTimes());
            rb.setMaxCatchTimes(this.getMaxCatchtTimes());
            for (CatchRecord catchRecord : this.getCatchRecords()) {
                rb.addCatchRecords(catchRecord.pack());
            }
            return rb.build();
        }

        public static class CatchRecord {
            private int InfoId;
            private int num;

            public int getInfoId() {
                return InfoId;
            }

            public void setInfoId(int infoId) {
                InfoId = infoId;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public CatchRecord(int infoId, int num) {
                InfoId = infoId;
                this.num = num;
            }

            public CatchRecord() {
            }

            public com.leocool.sgland.protocol.Data.CatchRecords pack() {
                com.leocool.sgland.protocol.Data.CatchRecords.Builder rb = com.leocool.sgland.protocol.Data.CatchRecords.newBuilder();
                rb.setInfoId(this.getInfoId());
                rb.setNum(this.getNum());
                return rb.build();
            }
        }
    }
}
