# 🔰 LegendsClans
This is an open-source project and has not been assigned to the public.\
If you are interested in contributing to the project, make a `Pull Request`.

## 📚 For Developers
Take a look at the class [ClansAPI](https://github.com/DevNatan/LegendsClans/blob/master/src/main/java/me/devnatan/legendsproject/clans/api/ClansAPI.java) or and handle cancelable [ClanEvents](https://github.com/DevNatan/LegendsClans/tree/master/src/main/java/me/devnatan/legendsproject/clans/api/events).

### Examples
Returning a Member or a Clan object.
#### Member
```java
Reward reward = new Reward(rewardName, id, delay);
ClansAPI api = LegendsClans.getAPI();

// if "member" is null = probably the player never entered the server
Member member = api.getMember("DevNatan");

// if "clan" is null = player is not a member of any clan.
Clan clan = member.getClan();
```

#### Clan
```java
// by name
Clan clan = api.getClan("Github Developers");

// by tag input
// our tag is "&bGIT"
Clan clan1 = api.getClanByTag("GIT"); // work
Clan clan2 = api.getClanByTag("&bGIT"); // work
```

#### Clan Tag
```java
// Imagining a clan with the tag "&bGIT" will return exactly this.
Tag tag = clan.getTag();

// Returns "&bGIT"
String input = tag.getInput();

// Returns "GIT"
String formatted = tag.getInputFormatted();

// Check if the tag size is larger than the maximum allowed.
boolean bigger = tag.isBiggerThanMaxLen();
```

Tag is a standalone object and you can use it to simulate the clan tag on whatever kind of input you want.
It will return exactly the example above.
```java
Tag tag = Tag.fromInput("&bGIT");
```

#### Clan Memory
```java
Memory memory = clan.getMemory();

long founded = memory.getFounded(); // timestamp
Member member = memory.getFounder();

// an enumeration containing for example FRIENDLY_FIRE.
List<Option> options = memory.getOptions();
```

#### Permissioned Object.
**OBS: The Member interface and the Role class implement or extend Permissioned, which contains all the permissions data for a particular object. Differentiate the global permission, starting with `contains` and individual being `has`.**
```java
// the Permissioned object has COLLECTIVE permission to invite new members to the clan.
permissionedObject.containsPermission(Permission.INVITE_MEMBERS);

// the Permissioned object has INDIVIDUAL permission to invite new members to the clan.
permissionedObject.hasPermission(Permission.INVITE_MEMBERS);

member.containsPermission(...); // COLLECTIVE, the member and his Role.
member.hasPermission(...); // INDIVIDUAL, only the member.
```

#### Member
Combat information.
```java
Combat combat = member.getCombat();
int kills = combat.getKills();
int deaths = combat.getDeaths();
```

Activity.
```java
MemberActivity activity = member.getActivity();

// all the following values in "long" are timestamp.
long lastSeen = activity.getLastSeen();
long lastJoined = activity.getLastJoined();

/*
  This value changes when a member is banned or expelled from a clan, 
  when their combat information changes, when their permissions or role are changed
 */
long lastModified = activity.getLastModified();
```


## 👉 How to use
### Download
Download all source code.\
![Download](https://i.imgur.com/uAnLeK6.png)


### Releases
Download a stable or developing release (.jar file).\
![Releases](https://i.imgur.com/5aS4JTm.png)

### Then
1. Compile the source into a .jar file or download a release.
2. Put the file in your "/plugins" folder.
3. Set up the "config.yml" file as you want.
4. Turn on the server, enter and give "/clan".
