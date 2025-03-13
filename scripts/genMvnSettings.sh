mkdir $HOME/.m2/
cat <<EOF | tee $HOME/.m2/settings.xml
<settings>
  <servers>
    <server>
      <id>github</id>
      <username>${GITHUB_USERNAME}</username>
      <password>${GH_PACKAGES_TOKEN}</password>
    </server>
  </servers>
</settings>
EOF
cat $HOME/.m2/settings.xml